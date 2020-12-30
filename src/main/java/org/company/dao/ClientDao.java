package org.company.dao;

import org.company.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class ClientDao implements Dao<Client> {

    @PersistenceContext
    private EntityManager em;

    public ClientDao(final EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Client> get(final int id) {
        return Optional.ofNullable(em.find(Client.class, id));
    }

    @Override
    public List<Client> getAll() {
        Query query = em.createQuery("SELECT c FROM Client AS c");
        return query.getResultList();
    }

    @Override
    public void create(final Client client) {
        executeInsideTransaction(em -> em.persist(client));
    }

    @Override
    public void update(final Client client) {
        executeInsideTransaction(em -> em.merge(client));
    }

    @Override
    public void delete(final Client client) {
        executeInsideTransaction(em -> em.remove(client));
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

    public int login(String login, String password){
            List client=  em.createNativeQuery("select clients.client_id  from clients \n" +
                    "inner join accounts using(account_id)\n" +
                    "where accounts.account_login = ?\n" +
                    "and accounts.account_password = ?").setParameter(1, login).setParameter(2, password).getResultList();

        if(client.isEmpty()){
            return 0;
        } else {
            return (int) client.get(0);
        }

    }
}
