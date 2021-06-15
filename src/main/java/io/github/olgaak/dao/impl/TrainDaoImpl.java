package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.TrainDao;
import io.github.olgaak.dto.TrainDto;
import io.github.olgaak.entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TrainDaoImpl implements TrainDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Train createNewTrain(Train train) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(train);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
            return train;
        }
    }

    public List<Train> getAllTrains() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Train> trains = null;
        try {
            Query query = entityManager.createQuery("SELECT t FROM Train t");
            trains = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return trains;
    }

    public Train getTrainById(long id){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Train train = null;
        try {
            Query query = entityManager.createQuery("SELECT t FROM Train t WHERE t.id = :id" +
                    "").setParameter("id", id);
            train = (Train) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return train;
    }


    @Transactional
    public void deleteTrain(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Train train = entityManager.find(Train.class, id);
            entityManager.remove(train);
//            Query query = entityManager
//                    .createQuery("DELETE FROM Train t WHERE t.id  = :trainId")
//                    .setParameter("trainId", id);
//            query.executeUpdate();
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
    public void editTrain(Train train) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(train);
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
