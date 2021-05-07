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
public class RouteDaoImpl implements RouteDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

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

    @Transactional
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

    @Transactional
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

    @Transactional
    public List<Route> getTrainRoutesByQuery(TrainQueryDto trainQuery) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Route> routes = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT r FROM Route r JOIN TimetableItem t on r.id = t.route.id " +
                            "JOIN TimetableItem t2 on r.id = t2.route.id " +
                            "WHERE t2.departureDate = '2021-05-10' " +
                            "and t.departureDate = '2021-05-10' " +
                            "and t2.station.id = 1 and t.station.id = 3" +
                            "and (t2.departureDate>t.departureDate or t2.departureTime>t.departureTime) ", Route.class);

            routes = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return routes;
    }

    @Override
    public void deleteRoute(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Route route = entityManager.find(Route.class, id);
            entityManager.remove(route);
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

    @Override
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
