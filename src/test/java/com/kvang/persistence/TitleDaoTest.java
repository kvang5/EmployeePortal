package com.kvang.persistence;

import com.kvang.entity.Title;
import lombok.extern.log4j.Log4j;
import org.hibernate.criterion.MatchMode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * The type Title dao test.
 */
@Log4j
public class TitleDaoTest {

    TitleDao titleDao;
    Title title;
    int newTitle = 0;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
        titleDao = new TitleDao();
        title = new Title();
        title.setJobTitle("Registered Nurse");
        titleDao.addTitle(title);

    }

    /**
     * Gets all titles.
     *
     * @throws Exception the exception
     */
    @Test
    public void getAllTitles() throws Exception {
        newTitle = titleDao.addTitle(title);
        List<Title> titles = titleDao.getAllTitles();
        assertTrue(titles.size() > 0);

    }

    /**
     * Gets title by id.
     *
     * @throws Exception the exception
     */
    @Test
    public void getTitleById() throws Exception {
        List<Title> titles = titleDao.getAllTitles();
        assertNotNull("No title returned", titles.get(1));
        log.info("title id is: " + titles.get(0).getTitleId() +  ", job title is: " + titles.get(0).getJobTitle());
        assertEquals("Title Id not return correctly", 1, titles.get(0).getTitleId());
        assertEquals("Job title not return correctly", "Agency Director", titles.get(0).getJobTitle());
    }


    /**
     * Add title.
     *
     * @throws Exception the exception
     */
    @Test
    public void addTitle() throws Exception {
        newTitle = titleDao.addTitle(title);
        log.info(newTitle);
        log.info("Title id: " + newTitle + ", Job Title: " + titleDao.getTitleById(newTitle).getJobTitle());
        assertNotNull("No title returned", titleDao.getTitleById(newTitle));
        assertEquals("Title Id not return correctly", title.getTitleId(), titleDao.getTitleById(newTitle).getTitleId());
        assertEquals("Job title not return correctly", title.getJobTitle(), titleDao.getTitleById(newTitle).getJobTitle());

    }


    /**
     * Delete title.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteTitle() throws Exception {
        titleDao.addTitle(title);
        assertNotNull("Title is null", titleDao.getTitleById(title.getTitleId()));

        titleDao.deleteTitle(title.getTitleId());

    }

    /**
     * Update title.
     *
     * @throws Exception the exception
     */
    @Test
    public void updateTitle() throws Exception {
        newTitle = titleDao.addTitle(title);
        title.setJobTitle("Certified Nurse Assistant");
        titleDao.updateTitle(title);

        assertEquals("Job title not updated", title.getJobTitle(), titleDao.getTitleById(newTitle).getJobTitle());

    }

    /**
     * Find by property.
     *
     * @throws Exception the exception
     */
    @Test
    public void findByProperty() throws Exception {
        List<Title> titles1 = titleDao.findByProperty("jobTitle", "R", MatchMode.ANYWHERE);

        assertTrue(titles1.size() > 0);

        List<Title> titles2 = titleDao.findByProperty("jobTitle", "Certified Nurse Assistant", MatchMode.EXACT);

        assertTrue(titles2.size() > 0);
    }

}