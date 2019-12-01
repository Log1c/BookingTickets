package ua.logic.bookingTicket.repository;

import ua.logic.bookingTicket.entity.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class CustomizeTicketRepositoryImpl implements CustomizeTicketRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void delete(Object entity) {
        Ticket ticket = (Ticket) entity;
        ticket.setDeleted(true);
        em.merge(ticket); //not necessarily, only for 3 task
    }
}
