package org.company.dao;

import org.company.model.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LoanDao implements Dao<Loan> {

    @PersistenceContext
    private EntityManager em;

    public LoanDao(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Loan> get(final int id) {
        return Optional.ofNullable(em.find(Loan.class, id));
    }

    @Override
    public List<Loan> getAll() {
        Query query = em.createQuery("SELECT l FROM Loan AS l");
        return query.getResultList();
    }

    @Override
    public void create(final Loan loan) {
        executeInsideTransaction(em -> em.persist(loan));
    }

    @Override
    public void update(final Loan loan) {
        executeInsideTransaction(em -> em.merge(loan));
    }

    @Override
    public void delete(final Loan loan) {
        executeInsideTransaction(em -> em.remove(loan));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
