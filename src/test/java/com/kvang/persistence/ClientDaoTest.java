package com.kvang.persistence;

import com.kvang.entity.Client;
import com.kvang.entity.State;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

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

        state = stateDao.getStateById(54);
        //stateDao.getStateById(53);

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

    }

    @Test
    public void addClient() throws Exception {

    }

    @Test
    public void deleteClient() throws Exception {

    }

    @Test
    public void updateClient() throws Exception {

    }

}