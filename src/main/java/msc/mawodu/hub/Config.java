package msc.mawodu.hub;


import msc.mawodu.hub.controllers.*;
import msc.mawodu.hub.development.MockInMemoryNotesDatabase;
import msc.mawodu.hub.development.MockInMemoryPipelineMetadataDatabase;
import msc.mawodu.hub.files.FileResolver;
import msc.mawodu.hub.files.FilesystemFileResolver;
import msc.mawodu.hub.providers.BasePipelineDetailsDataProvider;
import msc.mawodu.hub.providers.BasePipelineOverviewDataProvider;
import msc.mawodu.hub.providers.PipelineDetailsDataProvider;
import msc.mawodu.hub.providers.PipelineOverviewDataProvider;
import msc.mawodu.hub.stores.FilenamesStore;
import msc.mawodu.hub.stores.FilenamesStoreMySQL;
import msc.mawodu.hub.stores.NotesStore;
import msc.mawodu.hub.stores.PipelineMetadataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableScheduling
public class Config implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }


    @Bean // NB. required for @Value annotation to work from application.properties file
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    // MOCKS - for development

    @Bean
    MockInMemoryNotesDatabase mockInMemoryNotesDatabase() {
        return new MockInMemoryNotesDatabase();
    }

//    @Bean
//    MockInMemoryFileNamesDatabase mockInMemoryFileNamesDatabase() {
//        return new MockInMemoryFileNamesDatabase();
//    }

    @Bean
    MockInMemoryPipelineMetadataDatabase mockInMemoryPipelineMetadataDatabase() {
        return new MockInMemoryPipelineMetadataDatabase();
    }

//    @Bean
//    StubPipelineOverviewDataProvider stubPipelineOverviewDataProvider() {
//        return new StubPipelineOverviewDataProvider();
//    }

//    @Bean
//    @Autowired
//    public MockPipelineDetailsProvider mockPipelineDetailsProvider(NotesStore notesDatabase, FilenamesStore filenamesDatabase) {
//        return new MockPipelineDetailsProvider(notesDatabase, filenamesDatabase);
//    }


    // MYSQL Stores implementations

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        ds.setUrl(environment.getProperty("spring.datasource.url"));
        ds.setUsername(environment.getProperty("spring.datasource.username"));
        ds.setPassword(environment.getProperty("jdbc.url"));
        return ds;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbc(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean
//    NotesStoreMySQL notesStoreMySQL(JdbcTemplate jdbc) {
//        return new NotesStoreMySQL(jdbc);
//    }

    @Bean
    FilenamesStoreMySQL filenamesStoreMySQL(JdbcTemplate jdbc) {
        return new FilenamesStoreMySQL(jdbc);
    }

//    @Bean
//    PipelineMetadataStoreMySQL pipelineMetadataStoreMySQL(JdbcTemplate jdbc) {
//        return new PipelineMetadataStoreMySQL(jdbc);
//    }

    // Core Dependencies

    @Bean
    FilesystemFileResolver filesystemFileResolver() {
        return new FilesystemFileResolver();
    }

    @Bean
    @Autowired
    BasePipelineOverviewDataProvider basePipelineOverviewDataProvider(PipelineMetadataStore metadataStore, FilenamesStore filenamesStore) {
        return new BasePipelineOverviewDataProvider(metadataStore, filenamesStore);
    }

    @Bean
    @Autowired
    public BasePipelineDetailsDataProvider basePipelineDetailsDataProvider(NotesStore notesDatabase, PipelineMetadataStore metadataStore, FilenamesStore filenamesDatabase) {
        return new BasePipelineDetailsDataProvider(notesDatabase, metadataStore, filenamesDatabase);
    }


    // Controllers

    @Autowired
    NotesUpdateController notesUpdateController(NotesStore notesStore) {
        return new NotesUpdateController(notesStore);
    }

    @Autowired
    PipelineController pipelineController(PipelineDetailsDataProvider pipelineDetailsDataProvider) {
        return new PipelineController(pipelineDetailsDataProvider);
    }

    @Autowired
    FileDownloadController fileDownloadController(FileResolver fileResolver) {
        return new FileDownloadController(fileResolver);
    }

    @Autowired
    FileUploadController fileUploadController(FileResolver fileResolver, FilenamesStore filenamesStore) {
        return new FileUploadController(fileResolver, filenamesStore);
    }

    @Autowired
    PipelineStatusController pipelineStatusController(PipelineMetadataStore metadataStore, FileResolver fileResolver) {
        return new PipelineStatusController(metadataStore, fileResolver);
    }

    @Autowired
    HomeController homeController(PipelineOverviewDataProvider overviewDataProvider) {
        return new HomeController(overviewDataProvider);
    }
}
