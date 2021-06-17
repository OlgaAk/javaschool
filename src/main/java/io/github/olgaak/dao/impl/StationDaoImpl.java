package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.StationDao;
import io.github.olgaak.entity.Route;
import io.github.olgaak.entity.Station;
import io.github.olgaak.entity.TimetableItem;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StationDaoImpl implements StationDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void createNewStation(Station station) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(station);
            entityManager.flush();
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
    public List<Station> getAllStations() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Station> stations = null;
        try {
            Query query = entityManager.createQuery("SELECT t FROM Station t");
            stations = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return stations;
    }

    @Override
    public void deleteStation(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Station station = entityManager.find(Station.class, id);
            entityManager.remove(station);
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
    public void editStation(Station station) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(station);
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
    public Station getStationById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Station station = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT s FROM Station s WHERE s.id  = :stationId", Station.class)
                    .setParameter("stationId", id);
            station = (Station) query.getSingleResult();
            //force hibernate to initialize because of lazy loading issue, no session error despite @transactional
            Hibernate.initialize(station.getTimetableItems());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return station;
    }
}
