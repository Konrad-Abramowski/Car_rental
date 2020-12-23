package org.company.dao;

import org.company.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class CarDao implements Dao<Car> {

    @PersistenceContext
    private EntityManager em;

    public CarDao(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Car> get(final int id) {
        return Optional.ofNullable(em.find(Car.class, id));
    }

    @Override
    public List<Car> getAll() {
        Query query = em.createQuery("SELECT c FROM Car AS c");
        return query.getResultList();
    }

    @Override
    public void create(final Car car) {
        executeInsideTransaction(em -> em.persist(car));
    }

    @Override
    public void update(final Car car) {
        executeInsideTransaction(em -> em.merge(car));
    }

    @Override
    public void delete(final Car car) {
        executeInsideTransaction(em -> em.remove(car));
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
