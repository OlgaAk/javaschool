package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.TimetableDao;
import io.github.olgaak.entity.RoutePlan;
import io.github.olgaak.entity.TimetableItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TimetableDaoImpl implements TimetableDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void createNewTimetableItem(TimetableItem timetableItem) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(timetableItem);
//            entityManager.flush();
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
    public List<TimetableItem> getAllTimetableItems() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM TimetableItem t");
        List<TimetableItem> timetableItems = query.getResultList();
        entityManager.close();
        return timetableItems;
    }

    @Transactional
    public List<TimetableItem> getRoutePlanTimetableItems(long routePlanId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        List<TimetableItem> timetableItems = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Query query = entityManager.createQuery("SELECT t FROM TimetableItem t where t.routePlan.id = :id")
                    .setParameter("id", routePlanId);
            timetableItems = query.getResultList();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return timetableItems;
    }

    @Override
    public void deleteTimetableItem(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            TimetableItem timetableItem = entityManager.find(TimetableItem.class, id);
            entityManager.remove(timetableItem);
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
    public void editTimetableItem(TimetableItem timetableItem) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(timetableItem);
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
