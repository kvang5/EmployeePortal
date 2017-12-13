package com.kvang.persistence;

import com.kvang.entity.*;
import lombok.extern.log4j.Log4j;
import org.hibernate.criterion.MatchMode;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The type Client note dao test.
 */
@Log4j
public class ClientNoteDaoTest {
    LocalDate now = LocalDate.now();
    StateDao stateDao;
    State state;
    TitleDao titleDao;
    Title title;
    EmployeeDao employeeDao;
    Employee employee;
    ClientDao clientDao;
    Client client;
    ClientNoteDao clientNoteDao;
    ClientNote clientNote;
    int newState;
    int newTitle;
    int newEmployee;
    int newClient;
    int newClientNote;

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

        titleDao = new TitleDao();
        title = new Title();
        title.setJobTitle("Registered Nurse");

        employeeDao = new EmployeeDao();

        employee = new Employee();
        employee.setFirst_name("ruser");
        employee.setLast_name("ruser");
        employee.setAddress1("123 State St.");
        employee.setCity("Madison");
        employee.setState(state);
        employee.setPostal_zip_code("12345");
        employee.setEmail("ruser@ruser.com");
        employee.setHome_phone("828-455-6682");
        employee.setMobile_phone("828-455-6682");
        employee.setState(state);
        employee.setTitle(title);
        employee.setPassword("test");
        employee.setStatus(true);

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

        clientNoteDao = new ClientNoteDao();

        clientNote = new ClientNote();
        clientNote.setDate(now);
        clientNote.setCare_time(2.0);
        clientNote.setDescription("Assist client to change bed sheets, wash one load of laundry, and cooked for client.");
        clientNote.setComments("N/A");
        clientNote.setEmployee(employee);
        clientNote.setClient(client);
    }

    /**
     * Gets all client notes.
     *
     * @throws Exception the exception
     */
    @Test
    public void getAllClientNotes() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);
        newClient = clientDao.addClient(client);
        newClientNote = clientNoteDao.addClientNote(clientNote);
        List<ClientNote> clientNotes = clientNoteDao.getAllClientNotes();
        assertTrue(clientNotes.size() > 0);
    }

    /**
     * Gets client note by id.
     *
     * @throws Exception the exception
     */
    @Test
    public void getClientNoteById() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);
        newClient = clientDao.addClient(client);
        newClientNote = clientNoteDao.addClientNote(clientNote);

        assertNotEquals("No client note returned", 0, newClientNote);
        assertEquals("Client note Id not returned correctly", clientNote.getClient_noteId(), clientNoteDao.getClientNoteById(newClientNote).getClient_noteId());
    }

    /**
     * Add client note.
     *
     * @throws Exception the exception
     */
    @Test
    public void addClientNote() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);
        newClient = clientDao.addClient(client);
        newClientNote = clientNoteDao.addClientNote(clientNote);

        assertNotEquals("No client note returned", 0, newClientNote);
        assertEquals("Client note Id not returned correctly", clientNote.getClient_noteId(), clientNoteDao.getClientNoteById(newClientNote).getClient_noteId());
        assertEquals("Client note date not returned correctly", clientNote.getDate(), clientNoteDao.getClientNoteById(newClientNote).getDate());
        assertEquals("Client note care time not returned correctly", clientNote.getCare_time(), clientNoteDao.getClientNoteById(newClientNote).getCare_time());
        assertEquals("Client note description not returned correctly", clientNote.getDescription(), clientNoteDao.getClientNoteById(newClientNote).getDescription());
        assertEquals("Client note comments not returned correctly", clientNote.getComments(), clientNoteDao.getClientNoteById(newClientNote).getComments());
        assertEquals("Client note client id not returned correctly", clientNote.getClient().getClientId(), clientNoteDao.getClientNoteById(newClientNote).getClient().getClientId());
        assertEquals("Client note employee id not returned correctly", clientNote.getEmployee().getEmployeeId(), clientNoteDao.getClientNoteById(newClientNote).getEmployee().getEmployeeId());
    }

    /**
     * Delete client note.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteClientNote() throws Exception {
        newState = stateDao.addState(state);
        newTitle = titleDao.addTitle(title);
        newEmployee = employeeDao.addEmployee(employee);
        newClient = clientDao.addClient(client);
        newClientNote = clientNoteDao.addClientNote(clientNote);

        assertNotNull("Client note is null", clientNoteDao.getClientNoteById(clientNote.getClient_noteId()));

        clientNoteDao.deleteClientNote(clientNote.getClient_noteId());
    }

    /**
     * Update client note.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateClientNote() throws Exception {
        state = stateDao.getStateById(30);
        title = titleDao.getTitleById(2);
        employee = employeeDao.getEmployeeById(2);
        client = clientDao.getClientById(1);

        newClientNote = clientNoteDao.addClientNote(clientNote);
        clientNote.setDate(LocalDate.of(2017, 12, 1));
        clientNote.setCare_time(4.75);
        clientNote.setDescription("Cook breakfast, bathe and dress client, made bed, clean bedroom and clean living room.");
        clientNote.setComments("N/A");
        clientNote.setEmployee(employee);
        clientNote.setClient(client);

        clientNoteDao.updateClientNote(clientNote);

        //log.info("#1 " + clientNote.getDate());
        //log.info("#2 " + clientNoteDao.getClientNoteById(newClientNote).getDate());

        assertEquals("Client note date not updated", clientNote.getDate(), clientNoteDao.getClientNoteById(newClientNote).getDate());
        assertEquals("Client note care time not updated", clientNote.getCare_time(), clientNoteDao.getClientNoteById(newClientNote).getCare_time());
        assertEquals("Client note description not updated", clientNote.getDescription(), clientNoteDao.getClientNoteById(newClientNote).getDescription());
        assertEquals("Client note comments not updated", clientNote.getComments(), clientNoteDao.getClientNoteById(newClientNote).getComments());
        assertEquals("Client note client id not updated", clientNote.getClient().getClientId(), clientNoteDao.getClientNoteById(newClientNote).getClient().getClientId());
        assertEquals("Client note employee id not updated", clientNote.getEmployee().getEmployeeId(), clientNoteDao.getClientNoteById(newClientNote).getEmployee().getEmployeeId());
    }

    /**
     * Find by property.
     *
     * @throws Exception the exception
     */
    @Test
    public void findByProperty() throws Exception {
        List<ClientNote> clientNotes1 = clientNoteDao.findByProperty("comments", "A", MatchMode.ANYWHERE);
        assertTrue(clientNotes1.size() > 0);

        List<ClientNote> clientNotes2 = clientNoteDao.findByProperty("comments", "N/A", MatchMode.EXACT);
        assertTrue(clientNotes2.size() > 0);

    }

    @Test
    public void addClientNoteFromEmployee() throws Exception {
        List<ClientNote> clientNotes = clientNoteDao.getAllClientNotes();
        clientNoteDao.addClientNoteFromEmployee(1, now, 2.2, "Testing description", "Testing comments", "ruser@ruser.com");

        assertTrue(clientNotes.size() > 0);
    }

    @Test
    public void getClientNotesByDate() throws Exception {
        List<ClientNote> clientNotes = clientNoteDao.getAllClientNotes();

        //log.info("clientNotes.size(): " + clientNotes.size());

        assertTrue(clientNotes.size() > 0);

        List<ClientNote> clientNotesReturned1 = clientNoteDao.getClientNotesByDate(LocalDate.of(2017,12, 1));

        //log.info("clientNotesReturned1.size(): " + clientNotesReturned1.size());

        assertEquals("note in list of returns did not match ",1, clientNotesReturned1.size());

        List<ClientNote> clientNotesReturned2 = clientNoteDao.getClientNotesByDate(now);

        //log.info("clientNotesReturned2.size(): " + clientNotesReturned2.size());

        assertEquals("note in list of returns did not match ",3, clientNotesReturned2.size());

    }

    @Test
    public void getClientNotesByClientId() throws Exception {
        List<ClientNote> clientNotes = clientNoteDao.getAllClientNotes();

        log.info("clientNotes.size(): " + clientNotes.size());

        assertTrue(clientNotes.size() > 0);

        List<ClientNote> clientNoteList = clientNoteDao.getClientNotesByClientId(1);

        log.info(clientNoteList.size());

        assertEquals(1, clientNoteList.size());

    }


}