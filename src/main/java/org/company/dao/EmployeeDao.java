package org.company.dao;

import org.company.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class EmployeeDao implements Dao<Employee> {

    @PersistenceContext
    private EntityManager em;

    public EmployeeDao(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Employee> get(final int id) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public List<Employee> getAll() {
        Query query = em.createQuery("SELECT e FROM Employee AS e");
        return query.getResultList();
    }

    @Override
    public void create(final Employee employee) {
        executeInsideTransaction(em -> em.persist(employee));
    }

    @Override
    public void update(final Employee employee) {
        executeInsideTransaction(em -> em.merge(employee));
    }

    @Override
    public void delete(final Employee employee) {
        executeInsideTransaction(em -> em.remove(employee));
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
