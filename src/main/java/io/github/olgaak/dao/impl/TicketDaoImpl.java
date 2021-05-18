package io.github.olgaak.dao.impl;

import io.github.olgaak.dao.api.TicketDao;
import io.github.olgaak.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void saveTicket(Ticket ticket) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(ticket);
            System.out.println(ticket.getId());
            entityManager.flush();
            System.out.println(ticket.getId());
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

    public List<Ticket> getUserTickets(long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Ticket> tickets = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT t FROM Ticket t WHERE t.passenger.user.id  = :userId", Ticket.class)
                    .setParameter("userId", userId);
            tickets = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return tickets;
    }

    @Transactional
    public void deleteTicket(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Query query = entityManager
                    .createQuery("delete FROM Ticket t WHERE t.id  = :ticketId")
                    .setParameter("ticketId", id);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }

    }
}
