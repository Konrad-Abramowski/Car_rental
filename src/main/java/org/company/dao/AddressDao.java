package org.company.dao;

import org.company.model.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class AddressDao implements Dao<Address> {

    @PersistenceContext
    private EntityManager em;

    public AddressDao(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Address> get(final int id) {
        return Optional.ofNullable(em.find(Address.class, id));
    }

    @Override
    public List<Address> getAll() {
        Query query = em.createQuery("SELECT a FROM Address AS a");
        return query.getResultList();
    }

    @Override
    public void create(final Address address) {
        executeInsideTransaction(em -> em.persist(address));
    }

    @Override
    public void update(final Address address) {
        executeInsideTransaction(em -> em.merge(address));
    }

    @Override
    public void delete(final Address address) {
        executeInsideTransaction(em -> em.remove(address));
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
