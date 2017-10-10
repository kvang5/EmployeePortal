package com.kvang.persistence;

import com.kvang.entity.Title;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TitleDaoTest {

    private final Logger logger = Logger.getLogger(this.getClass());
    TitleDao titleDao;
    Title title;
    int newTitle = 0;

    @Before
    public void setUp() throws Exception {
        titleDao = new TitleDao();
        title = new Title();
        title.setJobTitle("Test Title");

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
        logger.info("title id is: " + titleDao.getTitleById(newTitle).getTitleId() +  ", job title is: " + titleDao.getTitleById(newTitle).getJobTitle());
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
        titleDao.deleteTitle(title.getTitleId());

        assertNull("Title not deleted", titleDao.getTitleById(title.getTitleId()));
    }

    @Test
    public void updateTitle() throws Exception {
        newTitle = titleDao.addTitle(title);
        title.setJobTitle("Update Test");
        titleDao.updateTitle(title);

        assertEquals("Job title not updated", title.getJobTitle(), titleDao.getTitleById(newTitle).getJobTitle());

    }

}