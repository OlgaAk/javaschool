package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.TimetableDao;
import io.github.olgaak.entity.TimetableItem;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class TimetableDaoImpl implements TimetableDao {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("railway_app");

    public void createNewTimetableItem(TimetableItem timetableItem){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(timetableItem);
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

    public List<TimetableItem> getAllTimetableItems() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM TimetableItem t");
        List<TimetableItem> timetableItems = query.getResultList();
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
    public void editTimetableItem(TimetableItem timetableItem) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(timetableItem);
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
