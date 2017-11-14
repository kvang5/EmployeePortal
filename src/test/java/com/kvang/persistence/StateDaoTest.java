package com.kvang.persistence;

import com.kvang.entity.State;
import lombok.extern.log4j.Log4j;
import org.hibernate.criterion.MatchMode;
import org.junit.After;
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

    @Before
    public void setUp() throws Exception {
        stateDao = new StateDao();
        state = new State();
        state.setState_code("WI");
        state.setState_name("Wisconsin");

    }

    @After
    public void tearDown() throws Exception {
        if (newState != 0) {
            stateDao.deleteState(newState);
        }
    }

    @Test
    public void getAllStates() throws Exception {
        newState = stateDao.addState(state);
        List<State> states = stateDao.getAllStates();
        assertTrue(states.size() > 0);
    }

    @Test
    public void getStateById() throws Exception {
        newState = stateDao.addState(state);
        assertNotNull("No state returned", stateDao.getStateById(newState));
        log.info("state id is: " + stateDao.getStateById(newState).getStateId() + ", state code is: " + stateDao.getStateById(newState).getState_code() + ", state name is: " + stateDao.getStateById(newState).getState_name());
        assertEquals("State Id not return correctly", state.getStateId(), stateDao.getStateById(newState).getStateId());
        assertEquals("State code not return correctly", state.getState_code(), stateDao.getStateById(newState).getState_code());
        assertEquals("State name not return correctly", state.getState_name(), stateDao.getStateById(newState).getState_name());

    }

    @Test
    public void addState() throws Exception {
        newState = stateDao.addState(state);
        assertNotNull("No state returned", stateDao.getStateById(newState));
        assertEquals("State Id not return correctly", state.getStateId(), stateDao.getStateById(newState).getStateId());
        assertEquals("State code not return correctly", state.getState_code(), stateDao.getStateById(newState).getState_code());
        assertEquals("State name not return correctly", state.getState_name(), stateDao.getStateById(newState).getState_name());

    }


    @Test
    public void deleteState() throws Exception {
        stateDao.addState(state);
        assertNotNull("user is null", stateDao.getStateById(state.getStateId()));
        stateDao.deleteState(state.getStateId());
    }

    @Test
    public void updateState() throws Exception {
        newState = stateDao.addState(state);
        state.setState_code("NC");
        state.setState_name("North Carolina");
        stateDao.updateState(state);

        assertEquals("state code not updated", state.getState_code(), stateDao.getStateById(newState).getState_code());
        assertEquals("state name not updated", state.getState_name(), stateDao.getStateById(newState).getState_name());

    }

    @Test
    public void findByProperty() throws Exception {
        List<State> states1 = stateDao.findByProperty("state_name", "W", MatchMode.ANYWHERE);

        assertTrue(states1.size() > 0);

        List<State> states2 = stateDao.findByProperty("state_name", "Wisconsin", MatchMode.EXACT);

        assertTrue(states2.size() > 0);
    }

}