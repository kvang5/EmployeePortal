package com.kvang.persistence;

import com.kvang.entity.Title;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TitleDao {

    Logger logger = Logger.getLogger(this.getClass());

    public List<Title> getAllTitles() {
        List<Title> titles = new ArrayList<Title>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            titles = session.createCriteria(Title.class).list();
        } catch (HibernateException he) {
            logger.error("Error getting all titles", he);
        } catch (Exception e) {
            logger.error("General exception for getAllTitles is caught", e);
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
            logger.error("Error adding title", he);
        } catch (Exception e) {
            logger.error("General exception for addTitle() is caught", e);
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
            logger.error("Error getting title by id", he);
        } catch (Exception e) {
            logger.error("General exception for getTitleById() is caught", e);
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
            logger.error("Error deleting title", he);
        } catch (Exception e) {
            logger.error("General exception for deleteTitle() is caught", e);
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
            logger.error("Error updating title", he);
        } catch (Exception e) {
            logger.error("General exception for updateTitle() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
