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

@Log4j
public class TitleDao {
    
    public List<Title> getAllTitles() {
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
