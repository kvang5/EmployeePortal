package com.kvang.persistence;


import com.kvang.entity.State;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kvang on 9/25/17.
 */
@Log4j
public class StateDao {
    
    public List<State> getAllStates() {
        List<State> states = new ArrayList<State>();
        Session session = null;
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            states = session.createCriteria(State.class).list();
        } catch (HibernateException he) {
            log.error("Error getting all states", he);
        } catch (Exception e) {
            log.error("General exception for getAllStates is caught", e);
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
            log.error("Error adding state", he);
        } catch (Exception e) {
            log.error("General exception for addState() is caught", e);
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
            log.error("Error getting state by id", he);
        } catch (Exception e) {
            log.error("General exception for getStateById() is caught", e);
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
            log.error("Error deleting state", he);
        } catch (Exception e) {
            log.error("General exception for deleteState() is caught", e);
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
            log.error("Error updating state", he);
        } catch (Exception e) {
            log.error("General exception for updateState() is caught", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<State> findByProperty(String propertyName, String value, MatchMode matchMode){
        Session session = null;
        List<State> items = new ArrayList<State>();
        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            if (matchMode != null){
                items =  session.createCriteria(State.class).add(Restrictions.ilike(propertyName, value, matchMode)).list();
            }else{
                items =  session.createCriteria(State.class).add(Restrictions.ilike(propertyName, value, MatchMode.EXACT)).list();
            }
        } catch (HibernateException he) {
            log.error("Error getting State", he);
        } catch (NullPointerException e) {
            log.error("Error getting State they don't exist: ", e);

        } finally {
            if (session != null) {
                session.close();
            }
        }
        return items;
    }
}
