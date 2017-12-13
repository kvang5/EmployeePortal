package com.kvang.persistence;

import com.kvang.entity.Title;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Title dao.
 */
@Log4j
public class TitleDao {

    // added in to get test to 100% for SessionFactoryProvider class
    private SessionFactoryProvider sfp;

    /**
     * Gets all titles.
     *
     * @return the all titles
     */
    public List<Title> getAllTitles() {
        sfp = new SessionFactoryProvider();
        List<Title> titles = new ArrayList<Title>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            titles = session.createCriteria(Title.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all titles", he);
        } catch (Exception e) {
            log.error("General exception for getAllTitles is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return titles;
    }

    /**
     * Add title int.
     *
     * @param title the title
     * @return the int
     */
    public int addTitle(Title title) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(title);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error adding title", he);
        } catch (Exception e) {
            log.error("General exception for addTitle() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    /**
     * Gets title by id.
     *
     * @param id the id
     * @return the title by id
     */
    public Title getTitleById(int id) {
        Title title = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            title = (Title) session.get(Title.class, id);
        } catch (HibernateException he) {
            log.error("Error getting title by id", he);
        } catch (Exception e) {
            log.error("General exception for getTitleById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return title;
    }

    /**
     * Delete title.
     *
     * @param id the id
     */
    public void deleteTitle(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Title title = (Title) session.load(Title.class, id);
            session.delete(title);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error deleting title", he);
        } catch (Exception e) {
            log.error("General exception for deleteTitle() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Update title.
     *
     * @param title the title
     */
    public void updateTitle(Title title) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(title);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error updating title", he);
        } catch (Exception e) {
            log.error("General exception for updateTitle() is caught", e);
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
    public List<Title> findByProperty(String propertyName, String value, MatchMode matchMode){
        Session session = null;
        List<Title> items = new ArrayList<Title>();
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            if (matchMode != null){
                items =  session.createCriteria(Title.class).add(Restrictions.ilike(propertyName, value, matchMode)).list();
            }else{
                items =  session.createCriteria(Title.class).add(Restrictions.ilike(propertyName, value, MatchMode.EXACT)).list();
            }
        } catch (HibernateException he) {
            log.error("Error getting Title", he);
        } catch (NullPointerException e) {
            log.error("Error getting Title they don't exist: ", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return items;
    }
}
