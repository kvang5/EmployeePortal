package com.kvang.persistence;

import com.kvang.entity.ClientNote;
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
     * Gets client notes by date.
     *
     * @param date the date
     * @return the client notes by date
     */
    public List<ClientNote> getClientNotesByDate(LocalDate date) {
        List<ClientNote> clientNotes = new ArrayList<ClientNote>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(ClientNote.class);
            criteria.add(Restrictions.eq("date", date));
            clientNotes = criteria.list();
        } catch (HibernateException he) {
            log.error("Error getting client notes by date", he);
        } catch (Exception e) {
            log.error("General exception for getClientNotesByDate() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return clientNotes;

    }
}
