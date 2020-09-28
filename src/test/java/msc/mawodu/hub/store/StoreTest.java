package msc.mawodu.hub.store;

import msc.mawodu.hub.status.PipelineMetaData;
import msc.mawodu.hub.stores.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;


@RunWith(SpringJUnit4ClassRunner.class)
public class StoreTest {

    @Autowired
    private Environment applicationProperties;

    private FilenamesStore filenamesStore;
    private NotesStore notesStore;
    private PipelineMetadataStore metadataStore;


    @Before
    public void setup() {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource());
        filenamesStore = new FilenamesStoreMySQL(jdbc);
        notesStore = new NotesStoreMySQL(jdbc);
        metadataStore = new PipelineMetadataStoreMySQL(jdbc);
    }

    @Test
    public void shouldStoreAndRetreiveFileNamesCorrectly() {
        filenamesStore.registerNewFile("1", "test.txt");
        Assert.assertTrue(filenamesStore.fetchAllFilenames("1").contains("test.txt"));
    }

    @Test
    public void shouldStoreAndRetreiveNotesCorrectly() {
        notesStore.updatePipelineNotes("1", "hello world3");
        Assert.assertTrue(notesStore.fetchNotesByPipelineId("1").equals("hello world3"));
    }

    @Test
    public void shouldStoreAndMetaDataCorrectly() {
        metadataStore.registerNewPipeline(new PipelineMetaData("1", "state", "123", "desc"));
        metadataStore.registerNewPipeline(new PipelineMetaData("2", "state2", "321", "desc2"));
//        metadataStore.registerNewPipeline(new PipelineMetaData("2", "state2", "321", "desc2"));


        Assert.assertTrue(notesStore.fetchNotesByPipelineId("1").equals("hello world3"));
    }

    // NB. this needs to reflect actual db setup, should be run on dev environment only.
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/hub");
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }

}
