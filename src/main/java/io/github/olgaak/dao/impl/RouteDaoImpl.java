package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.RouteDao;
import io.github.olgaak.entity.Route;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RouteDaoImpl implements RouteDao {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("railway_app");

    public void createNewRoute(Route route){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(route);
//            entityManager.flush();
            transaction.commit();
        }
        catch (Exception ex){
            if(transaction != null){
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Route> getAllRoutes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM Route t");
        List<Route> routes = query.getResultList();
        return routes;
    }

    @Transactional
    public List<Route> getTrainRoutes(Long trainId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Route> routes = null;
        try{
            Query query = entityManager
                    .createQuery("SELECT r FROM Route r JOIN FETCH r.train WHERE r.train.id  = :trainId", Route.class)
                    .setParameter("trainId", trainId);
            routes = query.getResultList();
        } catch (Exception ex){
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
        }
        catch (Exception ex){
            if(transaction != null){
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
        }
        catch (Exception ex){
            if(transaction != null){
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
