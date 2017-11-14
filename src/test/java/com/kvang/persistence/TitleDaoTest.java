package com.kvang.persistence;

import com.kvang.entity.Title;
import lombok.extern.log4j.Log4j;
import org.hibernate.criterion.MatchMode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Log4j
public class TitleDaoTest {

    TitleDao titleDao;
    Title title;
    int newTitle = 0;

    @Before
    public void setUp() throws Exception {
        titleDao = new TitleDao();
        title = new Title();
        title.setJobTitle("Registered Nurse");

    }

    /*@After
    public void tearDown() throws Exception {
        if (newTitle != 0) {
            titleDao.deleteTitle(newTitle);
        }
    }*/

    @Test
    public void getAllTitles() throws Exception {
        newTitle = titleDao.addTitle(title);
        List<Title> titles = titleDao.getAllTitles();
        assertTrue(titles.size() > 0);
    }

    @Test
    public void getTitleById() throws Exception {
        newTitle = titleDao.addTitle(title);
        assertNotNull("No title returned", titleDao.getTitleById(newTitle));
        log.info("title id is: " + titleDao.getTitleById(newTitle).getTitleId() +  ", job title is: " + titleDao.getTitleById(newTitle).getJobTitle());
        assertEquals("Title Id not return correctly", title.getTitleId(), titleDao.getTitleById(newTitle).getTitleId());
        assertEquals("Job title not return correctly", title.getJobTitle(), titleDao.getTitleById(newTitle).getJobTitle());

    }

    @Test
    public void addTitle() throws Exception {
        newTitle = titleDao.addTitle(title);
        assertNotNull("No title returned", titleDao.getTitleById(newTitle));
        assertEquals("Title Id not return correctly", title.getTitleId(), titleDao.getTitleById(newTitle).getTitleId());
        assertEquals("Job title not return correctly", title.getJobTitle(), titleDao.getTitleById(newTitle).getJobTitle());

    }


    @Test
    public void deleteTitle() throws Exception {
        titleDao.addTitle(title);
        assertNotNull("Title is null", titleDao.getTitleById(title.getTitleId()));

        titleDao.deleteTitle(title.getTitleId());

    }

    @Test
    public void updateTitle() throws Exception {
        newTitle = titleDao.addTitle(title);
        title.setJobTitle("Certified Nurse Assistant");
        titleDao.updateTitle(title);

        assertEquals("Job title not updated", title.getJobTitle(), titleDao.getTitleById(newTitle).getJobTitle());

    }

    @Test
    public void findByProperty() throws Exception {
        List<Title> titles1 = titleDao.findByProperty("jobTitle", "R", MatchMode.ANYWHERE);

        assertTrue(titles1.size() > 0);

        List<Title> titles2 = titleDao.findByProperty("jobTitle", "Certified Nurse Assistant", MatchMode.EXACT);

        assertTrue(titles2.size() > 0);
    }

}