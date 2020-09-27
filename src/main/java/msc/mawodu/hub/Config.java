package msc.mawodu.hub;


import msc.mawodu.hub.controllers.*;
import msc.mawodu.hub.development.MockInMemoryFileNamesDatabase;
import msc.mawodu.hub.development.MockInMemoryNotesDatabase;
import msc.mawodu.hub.development.MockInMemoryPipelineMetadataDatabase;
import msc.mawodu.hub.files.*;
import msc.mawodu.hub.providers.BasePipelineDetailsDataProvider;
import msc.mawodu.hub.providers.PipelineDetailsDataProvider;
import msc.mawodu.hub.providers.BasePipelineOverviewDataProvider;
import msc.mawodu.hub.stores.FilenamesStore;
import msc.mawodu.hub.stores.NotesStore;
import msc.mawodu.hub.stores.PipelineMetadataStore;
import msc.mawodu.hub.providers.PipelineOverviewDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableScheduling
public class Config {


    @Bean // NB. required for @Value annotation to work from application.properties file
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    // MOCKS - for development

    @Bean
    MockInMemoryNotesDatabase mockInMemoryNotesDatabase() {
        return new MockInMemoryNotesDatabase();
    }

    @Bean
    MockInMemoryFileNamesDatabase mockInMemoryFileNamesDatabase() {
        return new MockInMemoryFileNamesDatabase();
    }

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
