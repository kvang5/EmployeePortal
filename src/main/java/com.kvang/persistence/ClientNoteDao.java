package com.kvang.persistence;

import com.kvang.entity.Client;
import com.kvang.entity.ClientNote;
import com.kvang.entity.Employee;
import lombok.extern.log4j.Log4j;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Client note dao.
 */
@Log4j
public class ClientNoteDao {

    /**
     * Gets all client notes.
     *
     * @return the all client notes
     */
    public List<ClientNote> getAllClientNotes() {
        List<ClientNote> clientNotes = new ArrayList<ClientNote>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            clientNotes = session.createCriteria(ClientNote.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all clientNotes", he);
        } catch (Exception e) {
            log.error("General exception is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return clientNotes;
    }

    /**
     * Gets client note by id.
     *
     * @param id the id
     * @return the client note by id
     */
    public ClientNote getClientNoteById(int id) {
        ClientNote clientNote = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            clientNote = (ClientNote) session.get(ClientNote.class, id);
            Hibernate.initialize(clientNote.getEmployee());
            Hibernate.initialize(clientNote.getClient());
        } catch (HibernateException he) {
            log.error("Error getting clientNote by id", he);
        } catch (Exception e) {
            log.error("General exception for getClientNoteById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return clientNote;
    }


    /**
     * Add client note int.
     *
     * @param clientNote the client note
     * @return the int
     */
    public int addClientNote(ClientNote clientNote) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(clientNote);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error adding clientNote", he);
        } catch (Exception e) {
            log.error("General exception for addClientNote() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    /**
     * Delete client note.
     *
     * @param id the id
     */
    public void deleteClientNote(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            ClientNote clientNote = (ClientNote) session.load(ClientNote.class, id);
            session.delete(clientNote);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error deleting clientNote", he);
        } catch (Exception e) {
            log.error("General exception for deleteClientNote() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Update client note.
     *
     * @param clientNote the client note
     */
    public void updateClientNote(ClientNote clientNote) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(clientNote);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error updating clientNote", he);
        } catch (Exception e) {
            log.error("General exception for updateClientNote() is caught", e);
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
    public List<ClientNote> findByProperty(String propertyName, String value, MatchMode matchMode) {
        Session session = null;
        List<ClientNote> items = new ArrayList<ClientNote>();
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            if (matchMode != null) {
                items = session.createCriteria(ClientNote.class).add(Restrictions.ilike(propertyName, value, matchMode)).list();
            } else {
                items = session.createCriteria(ClientNote.class).add(Restrictions.ilike(propertyName, value, MatchMode.EXACT)).list();
            }
        } catch (HibernateException he) {
            log.error("Error getting ClientNote", he);
        } catch (NullPointerException e) {
            log.error("Error getting ClientNote they don't exist: ", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return items;
    }

    /**
     * Employee adds client note about client
     *
     * @param cId      the c id
     * @param careDate the care date
     * @param careTime the care time
     * @param desc     the desc
     * @param comments the comments
     * @param empEmail the emp email
     */
// This method will save client note entered by employee
    public void addClientNoteFromEmployee(int cId, LocalDate careDate, Double careTime, String desc, String comments, String empEmail) {
        Session session = null;
        Transaction tx = null;
        Client client;
        ClientNote clientNote;
        EmployeeDao employeeDao;
        List<Employee> employeeList;
        int id = 0;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            tx = session.beginTransaction();
            employeeDao = new EmployeeDao();
            employeeList = employeeDao.findByProperty("email", empEmail, MatchMode.EXACT);
            client = (Client) session.get(Client.class, cId);
            clientNote = new ClientNote();
            clientNote.setClient(client);
            clientNote.setDate(careDate);
            clientNote.setCare_time(careTime);
            clientNote.setDescription(desc);
            clientNote.setComments(comments);
            clientNote.setEmployee(employeeList.get(0));
            id = (int) session.save(clientNote);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) tx.rollback();
            log.error("Hibernate exception error adding client note from employee: ", he);
        } catch (Exception e) {
            log.error("Exception error adding client note from employee: ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
