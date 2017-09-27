package com.kvang.persistence;


import com.kvang.entity.State;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvang on 9/25/17.
 */
public class StateDao {

    Logger logger = Logger.getLogger(this.getClass());

    public List<State> getAllStates() {
        List<State> states = new ArrayList<State>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            states = session.createCriteria(State.class).list();
        } catch (HibernateException he) {
            logger.error("Error getting all states", he);
        } catch (Exception e) {
            logger.error("General exception for getAllStates is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return states;
    }

    public int addState(State state) {
        int id = 0;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            id = (int) session.save(state);
            transaction.commit();
        } catch (HibernateException he) {
            logger.error("Error adding state", he);
        } catch (Exception e) {
            logger.error("General exception for addState() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public State getStateById(int id) {
        State state = null;
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            state = (State) session.get(State.class, id);
        } catch (HibernateException he) {
            logger.error("Error getting state by id", he);
        } catch (Exception e) {
            logger.error("General exception for getStateById() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return state;
    }

    public void deleteState(int id) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            State state = (State) session.load(State.class, id);
            session.delete(state);
            transaction.commit();
        } catch (HibernateException he) {
            logger.error("Error deleting state", he);
        } catch (Exception e) {
            logger.error("General exception for deleteState() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateState(State state) {
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(state);
            transaction.commit();
        } catch (HibernateException he) {
            logger.error("Error updating state", he);
        } catch (Exception e) {
            logger.error("General exception for updateState() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
