package com.kvang.persistence;

import com.kvang.entity.Client;
import com.kvang.entity.State;
import lombok.extern.log4j.Log4j;
import org.hibernate.criterion.MatchMode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kvang on 9/29/17.
 */
@Log4j
public class ClientDaoTest {
    StateDao stateDao;
    State state;
    ClientDao clientDao;
    Client client;
    int newState = 0;
    int newClient = 0;

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
        client.setStatus(true);
    }

    /**
     * Gets all clients.
     *
     * @throws Exception the exception
     */
    @Test
    public void getAllClients() throws Exception {
        newState = stateDao.addState(state);
        newClient = clientDao.addClient(client);
        List<Client> clients = clientDao.getAllClients();
        assertTrue(clients.size() > 0);
    }

    /**
     * Gets client by id.
     *
     * @throws Exception the exception
     */
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
        assertEquals("Client status not returned correctly", client.getStatus(), clientDao.getClientById(newClient).getStatus());
    }

    /**
     * Add client.
     *
     * @throws Exception the exception
     */
    @Test
    public void addClient() throws Exception {
        newState = stateDao.addState(state);
        //log.info("newState: " + newState);
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
        assertEquals("Client status not returned correctly", client.getStatus(), clientDao.getClientById(newClient).getStatus());
    }

    /**
     * Delete client.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteClient() throws Exception {
        stateDao.addState(state);
        clientDao.addClient(client);
        assertNotNull("Client is null", clientDao.getClientById(client.getClientId()));

        clientDao.deleteClient(client.getClientId());
    }

    /**
     * Update client.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateClient() throws Exception {
        newState = stateDao.addState(state);
        state.setStateId(stateDao.getStateById(5).getStateId());
        state.setState_code(stateDao.getStateById(5).getState_code());
        state.setState_name(stateDao.getStateById(5).getState_name());

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
        assertEquals("Client status not returned correctly", client.getStatus(), clientDao.getClientById(newClient).getStatus());
    }

    @Test
    public void getAllClientByEmployee() throws Exception {
        List<Client> clients = clientDao.getAllClients();
        clientDao.getAllClientByEmployee(clients.get(0).getEmail());
        //log.info(clients.size());
        assertTrue(clients.size() > 0);
    }

    @Test
    public void findByProperty() throws Exception {
        List<Client> clients1 = clientDao.findByProperty("first_name", "p", MatchMode.ANYWHERE);
        assertTrue(clients1.size() > 0);

        List<Client> clients2 = clientDao.findByProperty("first_name", "Patty", MatchMode.EXACT);
        assertTrue(clients2.size() > 0);

        List<Client> clients3 = clientDao.findByProperty("email", "ruser@ruser.com", MatchMode.EXACT);
        assertTrue(clients3.size() == 1);
        //log.info(clients3.get(0).getFirst_name());
    }

    @Test
    public void addNewClient() throws Exception {
        int nc = 0;
        nc = clientDao.addNewClient("ruser", "ruser", "123 setter street", "", "city",
                33, "12333", "ruser@ruser.com", "828-222-2222", "", true);

        assertTrue(nc != 0);
    }
}