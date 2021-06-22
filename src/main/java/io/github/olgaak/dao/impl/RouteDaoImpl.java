package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.RouteDao;
import io.github.olgaak.dto.TrainQueryDto;
import io.github.olgaak.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RouteDaoImpl implements RouteDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    public void createNewRoute(Route route) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(route);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Route> getAllRoutes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Route> routes = null;
        try {
            Query query = entityManager.createQuery("SELECT t FROM Route t");
            routes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return routes;
    }

    public List<Route> getTrainRoutes(Long trainId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Route> routes = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT r FROM Route r JOIN FETCH r.train WHERE r.train.id  = :trainId", Route.class)
                    .setParameter("trainId", trainId);
            routes = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return routes;
    }

    public Route getRouteById(Long routeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Route route = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT r FROM Route r join fetch RoutePlan p on r.routePlan.id = p.id join fetch TimetableItem t on r.routePlan.id = t.routePlan.id WHERE r.id  = :routeId", Route.class)
                    .setParameter("routeId", routeId);
            route = (Route) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return route;
    }


    public List<Route> getTrainRoutesByQuery(TrainQueryDto trainQuery) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Route> routes = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT r FROM Route r " +
                            "JOIN RoutePlan rp on r.routePlan.id = rp.id " +
                            "JOIN TimetableItem t on rp.id = t.routePlan.id " +
                            "JOIN TimetableItem t2 on rp.id = t2.routePlan.id " +
                            "WHERE r.departureDate = :departureDate " +
                            "and t2.station.id = :arrivalStation and t.station.id = :departureStation " +
                            "and t2.arrivalTime > t.departureTime", Route.class)
                    .setParameter("departureDate", trainQuery.getDepartureDate())
                    .setParameter("arrivalStation", trainQuery.getArrivalStationId())
                    .setParameter("departureStation", trainQuery.getDepartureStationId());
            routes = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return routes;
    }

    @Transactional
    public void deleteRoute(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
//            Route route = entityManager.find(Route.class, id);
//            entityManager.remove(route); // doesnt work
            Query query = entityManager
                    .createQuery("DELETE FROM Route r WHERE r.id  = :routeId")
                    .setParameter("routeId", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Transactional
    public void editRoute(Route route) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(route);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
