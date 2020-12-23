package org.company.dao;

import org.company.model.Account;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class AccountDao implements Dao<Account> {

    @PersistenceContext
    private EntityManager em;

    public AccountDao(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Account> get(final int id) {
        return Optional.ofNullable(em.find(Account.class, id));
    }

    @Override
    public List<Account> getAll() {
        Query query = em.createQuery("SELECT a FROM Account AS a");
        return query.getResultList();
    }

    @Override
    public void create(final Account account) {
        executeInsideTransaction(em -> em.persist(account));
    }

    @Override
    public void update(final Account account) {
        executeInsideTransaction(em -> em.merge(account));
    }

    @Override
    public void delete(final Account account) {
        executeInsideTransaction(em -> em.remove(account));
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

