package com.kvang.persistence;

import com.kvang.entity.Client;
import com.kvang.entity.State;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kvang on 9/29/17.
 */
public class ClientDaoTest {
    private final Logger logger = Logger.getLogger(this.getClass());
    StateDao stateDao;
    State state;

    ClientDao clientDao;
    Client client;

    int newState = 0;
    int newClient = 0;

    @Before
    public void setUp() throws Exception {
        stateDao = new StateDao();

        state = new State();
        state.setState_code("CT");
        state.setState_name("Client Testing");

        clientDao = new ClientDao();

        client = new Client();
        client.setFirst_name("Patty");
        client.setLast_name("Smith");
        client.setAddress1("908 Washington Street");
        client.setCity("Madison");
        client.setPostal_zip_code("53590");
        client.setEmail("psmith@gmail.com");
        client.setHome_phone("608-222-2345");
        client.setMobile_phone("608-223-2323");
        client.setState(state);
    }

    @After
    public void tearDown() throws Exception {
        /*if (newState != 0) {
            stateDao.deleteState(newState);
        }

        if (newClient != 0) {
            clientDao.deleteClient(newClient);
        }*/
    }

    @Test
    public void getAllClients() throws Exception {
        newState = stateDao.addState(state);
        newClient = clientDao.addClient(client);
        List<Client> clients = clientDao.getAllClients();
        assertTrue(clients.size() > 0);
    }

    @Test
    public void getClientById() throws Exception {
        newState = stateDao.addState(state);
        newClient = clientDao.addClient(client);
        
        assertNotEquals("No client returned", 0, newClient);
        assertEquals("Client Id not returned correctly", client.getClientId(), clientDao.getClientById(newClient).getClientId());
        assertEquals("Client first name not returned correctly", client.getFirst_name(), clientDao.getClientById(newClient).getFirst_name());
        assertEquals("Client last name not returned correctly", client.getLast_name(), clientDao.getClientById(newClient).getLast_name());
        assertEquals("Client address1 not returned correctly", client.getAddress1(), clientDao.getClientById(newClient).getAddress1());
        assertEquals("Client address2 not returned correctly", client.getAddress2(), clientDao.getClientById(newClient).getAddress2());
        assertEquals("Client city not returned correctly", client.getCity(), clientDao.getClientById(newClient).getCity());
        assertEquals("Client postal zip code not returned correctly", client.getPostal_zip_code(), clientDao.getClientById(newClient).getPostal_zip_code());
        assertEquals("Client email not returned correctly", client.getEmail(), clientDao.getClientById(newClient).getEmail());
        assertEquals("Client home phone not returned correctly", client.getHome_phone(), clientDao.getClientById(newClient).getHome_phone());
        assertEquals("Client mobile phone not returned correctly", client.getMobile_phone(), clientDao.getClientById(newClient).getMobile_phone());
        assertEquals("Client state not returned correctly", client.getState().getState_code(), clientDao.getClientById(newClient).getState().getState_code());

    }

    @Test
    public void addClient() throws Exception {
        newState = stateDao.addState(state);
        logger.info("newState: " + newState);
        newClient = clientDao.addClient(client);

        assertNotEquals("No new client added", 0, newClient);
        assertEquals("Client Id not returned correctly", client.getClientId(), clientDao.getClientById(newClient).getClientId());
        assertEquals("Client first name not returned correctly", client.getFirst_name(), clientDao.getClientById(newClient).getFirst_name());
        assertEquals("Client last name not returned correctly", client.getLast_name(), clientDao.getClientById(newClient).getLast_name());
        assertEquals("Client address1 not returned correctly", client.getAddress1(), clientDao.getClientById(newClient).getAddress1());
        assertEquals("Client address2 not returned correctly", client.getAddress2(), clientDao.getClientById(newClient).getAddress2());
        assertEquals("Client city not returned correctly", client.getCity(), clientDao.getClientById(newClient).getCity());
        assertEquals("Client postal zip code not returned correctly", client.getPostal_zip_code(), clientDao.getClientById(newClient).getPostal_zip_code());
        assertEquals("Client email not returned correctly", client.getEmail(), clientDao.getClientById(newClient).getEmail());
        assertEquals("Client home phone not returned correctly", client.getHome_phone(), clientDao.getClientById(newClient).getHome_phone());
        assertEquals("Client mobile phone not returned correctly", client.getMobile_phone(), clientDao.getClientById(newClient).getMobile_phone());
        assertEquals("Client state not returned correctly", client.getState().getState_code(), clientDao.getClientById(newClient).getState().getState_code());

    }

    @Test
    public void deleteClient() throws Exception {
        stateDao.addState(state);
        clientDao.addClient(client);
        clientDao.deleteClient(client.getClientId());
        assertNull("Client not deleted", clientDao.getClientById(client.getClientId()));

    }

    @Test
    public void updateClient() throws Exception {
        newState = stateDao.addState(state);
        state.setStateId(stateDao.getStateById(55).getStateId());
        state.setState_code(stateDao.getStateById(55).getState_code());
        state.setState_name(stateDao.getStateById(55).getState_name());

        newClient = clientDao.addClient(client);
        client.setFirst_name("Helen");
        client.setLast_name("Keller");
        client.setAddress1("2020 Park Street");
        client.setAddress2("Apt. A");
        client.setCity("Sun Prairie");
        client.setPostal_zip_code("53590");
        client.setEmail("hkeller@gmail.com");
        client.setHome_phone("101-345-0987");
        client.setMobile_phone("342-576-0082");
        client.setState(state);

        clientDao.updateClient(client);

        assertNotNull("State is null: " + state);
        assertNotNull("Client is null: " + client);
        assertEquals("Client first name not updated", client.getFirst_name(), clientDao.getClientById(newClient).getFirst_name());
        assertEquals("Client last name not updated", client.getLast_name(), clientDao.getClientById(newClient).getLast_name());
        assertEquals("Client address1 name not updated", client.getAddress1(), clientDao.getClientById(newClient).getAddress1());
        assertEquals("Client address2 name not updated", client.getAddress2(), clientDao.getClientById(newClient).getAddress2());
        assertEquals("Client city name not updated", client.getCity(), clientDao.getClientById(newClient).getCity());
        assertEquals("Client postal zip code name not updated", client.getPostal_zip_code(), clientDao.getClientById(newClient).getPostal_zip_code());
        assertEquals("Client email name not updated", client.getEmail(), clientDao.getClientById(newClient).getEmail());
        assertEquals("Client home phone name not updated", client.getHome_phone(), clientDao.getClientById(newClient).getHome_phone());
        assertEquals("Client mobile phone name not updated", client.getMobile_phone(), clientDao.getClientById(newClient).getMobile_phone());
        assertEquals("Client state not updated", client.getState().getState_code(), clientDao.getClientById(newClient).getState().getState_code());
    }
}