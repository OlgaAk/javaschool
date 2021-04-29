package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.TrainDao;
import io.github.olgaak.entity.Train;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TrainDaoImpl implements TrainDao {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("railway_app");

    public void createNewTrain(Train train){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(train);
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
    @Transactional
    public List<Train> getAllTrains() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM Train t");
        List<Train> trains = query.getResultList();
        return trains;
    }

    @Override
    public void deleteTrain(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Train train = entityManager.find(Train.class, id);
            entityManager.remove(train);
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
    public void editTrain(Train train) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(train);
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
