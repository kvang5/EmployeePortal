package com.kvang.persistence;


import com.kvang.entity.Client;
import com.kvang.entity.State;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvang on 9/28/17.
 */
@Log4j
public class ClientDao {

    /**
     * Gets all clients.
     *
     * @return the all clients
     */
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

    //TODO: write testing for this method
    public List<Client> getAllClientByEmployee(String email) {
        List<Client> clients = new ArrayList<Client>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Query query = session.createQuery("select c from Client c inner join c.employeeSet e where e.email = :email");
            query.setParameter("email", email);
            clients = query.list();
            if (clients.isEmpty()) {
                log.info("clients list is empty");
                return null;
            } else {
                //log.info("Inside the else");
                for (Client c : clients) {
                    //log.info("inside the for loop");
                    log.info(c.getClientId() + " " + c.getFirst_name());
                }
                //log.info("outside the for loop");
                //log.info("clients size : " + clients.size());
                return clients;
            }
        } catch (HibernateException he) {
            log.error("Error getting all clients by employee", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return clients;
    }

    /**
     * Gets client by id.
     *
     * @param id the id
     * @return the client by id
     */
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


    /**
     * Add client int.
     *
     * @param client the client
     * @return the int
     */
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

    /**
     * Delete client.
     *
     * @param id the id
     */
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

    /**
     * Update client.
     *
     * @param client the client
     */
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

    /**
     * Find by property list.
     *
     * @param propertyName the property name
     * @param value        the value
     * @param matchMode    the match mode
     * @return the list
     */
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

    //TODO: write test for this method
    public void addNewClient(String first_name, String last_name, String address1, String address2, String city, int sId,
                             String postal_zip_code, String email, String home_phone, String mobile_phone, boolean statusChecked) {
        Session session = null;
        Transaction tx = null;
        State state;
        Client client;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            tx = session.beginTransaction();
            state = (State) session.get(State.class, sId);
            client = new Client();
            client.setFirst_name(first_name);
            client.setLast_name(last_name);
            client.setAddress1(address1);
            client.setAddress2(address2);
            client.setCity(city);
            client.setState(state);
            client.setPostal_zip_code(postal_zip_code);
            client.setEmail(email);
            client.setHome_phone(home_phone);
            client.setMobile_phone(mobile_phone);
            client.setStatus(statusChecked);
            session.save(client);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            log.info("Error saving client: ", he);
        } catch (Exception e) {
            log.error("Client was not added through sign up form: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
