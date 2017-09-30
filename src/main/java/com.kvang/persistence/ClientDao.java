package com.kvang.persistence;


import com.kvang.entity.Client;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvang on 9/28/17.
 */
public class ClientDao {
    private final Logger logger = Logger.getLogger(this.getClass());


    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<Client>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            clients = session.createCriteria(Client.class).list();
        } catch (HibernateException he) {
            logger.error("Error getting all clients", he);
        } catch (Exception e) {
            logger.error("General exception is caught", e);
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

        } catch (HibernateException he) {
            logger.error("Error getting client by id", he);
        } catch (Exception e) {
            logger.error("General exception for getClientById() is caught", e);
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
            logger.error("Error adding client", he);
        } catch (Exception e) {
            logger.error("General exception for addClient() is caught", e);
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
            logger.error("Error deleting client", he);
        } catch (Exception e) {
            logger.error("General exception for deleteClient() is caught", e);
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
            logger.error("Error updating client", he);
        } catch (Exception e) {
            logger.error("General exception for updateClient() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
