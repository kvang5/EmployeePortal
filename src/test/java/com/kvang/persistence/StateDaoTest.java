package com.kvang.persistence;

import com.kvang.entity.State;
import lombok.extern.log4j.Log4j;
import org.hibernate.criterion.MatchMode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kvang on 9/26/17.
 */
@Log4j
public class StateDaoTest {
    StateDao stateDao;
    State state;
    int newState = 0;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        stateDao = new StateDao();
        state = new State();
        state.setState_code("WI");
        state.setState_name("Wisconsin");
        newState = stateDao.addState(state);
    }

    /**
     * Gets all states.
     *
     * @throws Exception the exception
     */
    @Test
    public void getAllStates() throws Exception {
        List<State> states = stateDao.getAllStates();
        assertTrue(states.size() > 0);
    }

    /**
     * Gets state by id.
     *
     * @throws Exception the exception
     */
    @Test
    public void getStateById() throws Exception {
        List<State> states = stateDao.getAllStates();
        assertNotNull("No state returned", states.get(32));
        log.info("State id: " + states.get(32).getStateId() + ", State code: " + states.get(32).getState_code() + " State name: " + states.get(32).getState_name());
        assertEquals("State code not return correctly", "NC", states.get(32).getState_code());
        assertEquals("State name not return correctly", "North Carolina", states.get(32).getState_name());

    }

    /**
     * Add state.
     *
     * @throws Exception the exception
     */
    @Test
    public void addState() throws Exception {
        newState = stateDao.addState(state);
        log.info(newState);
        log.info("State id: " + newState + ", State code: " + stateDao.getStateById(newState).getState_code() + ", State name: " + stateDao.getStateById(newState).getState_name());
        assertNotNull("No state returned", stateDao.getStateById(newState));
        assertEquals("State Id not return correctly", state.getStateId(), stateDao.getStateById(newState).getStateId());
        assertEquals("State code not return correctly", state.getState_code(), stateDao.getStateById(newState).getState_code());
        assertEquals("State name not return correctly", state.getState_name(), stateDao.getStateById(newState).getState_name());

    }


    /**
     * Delete state.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteState() throws Exception {
        List<State> states = stateDao.getAllStates();
        assertNotNull("state is null", stateDao.getStateById(states.get(33).getStateId()));
        stateDao.deleteState(states.get(40).getStateId());
    }

    /**
     * Update state.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateState() throws Exception {
        newState = stateDao.addState(state);
        state.setState_code("NC");
        state.setState_name("North Carolina");
        stateDao.updateState(state);

        assertEquals("state code not updated", state.getState_code(), stateDao.getStateById(newState).getState_code());
        assertEquals("state name not updated", state.getState_name(), stateDao.getStateById(newState).getState_name());

    }

    /**
     * Find by property.
     *
     * @throws Exception the exception
     */
    @Test
    public void findByProperty() throws Exception {
        List<State> states1 = stateDao.findByProperty("state_name", "W", MatchMode.ANYWHERE);

        assertTrue(states1.size() > 0);

        List<State> states2 = stateDao.findByProperty("state_name", "Wisconsin", MatchMode.EXACT);

        assertTrue(states2.size() > 0);
    }

}