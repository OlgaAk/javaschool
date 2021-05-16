package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.UserDao;
import io.github.olgaak.entity.Ticket;
import io.github.olgaak.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    @Transactional
    public User findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT u FROM User u WHERE u.email  = :email", User.class)
                    .setParameter("email", email);
            user = (User) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    @Transactional
    public void createNewUser(User user) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.persist(user);
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
