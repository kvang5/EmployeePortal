package com.kvang.persistence;


import com.kvang.entity.Client;
import lombok.extern.log4j.Log4j;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvang on 9/28/17.
 */
@Log4j
public class ClientDao {

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<Client>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            clients = session.createCriteria(Client.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all clients", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return clients;
    }

    public Client getClientById(int id) {
        Client client = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            client = (Client) session.get(Client.class, id);
            Hibernate.initialize(client.getState());
        } catch (HibernateException he) {
            log.error("Error getting client by id", he);
        } catch (Exception e) {
            log.error("General exception for getClientById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return client;
    }


    public int addClient(Client client) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(client);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error adding client", he);
        } catch (Exception e) {
            log.error("General exception for addClient() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void deleteClient(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Client client = (Client) session.load(Client.class, id);
            session.delete(client);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error deleting client", he);
        } catch (Exception e) {
            log.error("General exception for deleteClient() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateClient(Client client) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error updating client", he);
        } catch (Exception e) {
            log.error("General exception for updateClient() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Client> findByProperty(String propertyName, String value, MatchMode matchMode) {
        Session session = null;
        List<Client> items = new ArrayList<Client>();
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            if (matchMode != null) {
                items = session.createCriteria(Client.class).add(Restrictions.ilike(propertyName, value, matchMode)).list();
            } else {
                items = session.createCriteria(Client.class).add(Restrictions.ilike(propertyName, value, MatchMode.EXACT)).list();
            }
        } catch (HibernateException he) {
            log.error("Error getting Client", he);
        } catch (NullPointerException e) {
            log.error("Error getting Client they don't exist: ", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return items;
    }

}
