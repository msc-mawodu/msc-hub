package msc.mawodu.hub;


import msc.mawodu.hub.development.*;
import msc.mawodu.hub.files.*;
import msc.mawodu.hub.notes.NotesUpdateController;
import msc.mawodu.hub.status.BasePipelineOverviewDataProvider;
import msc.mawodu.hub.pipelines.PipelineController;
import msc.mawodu.hub.pipelines.PipelineDetailsDataProvider;
import msc.mawodu.hub.status.PipelineOverviewDataProvider;
import msc.mawodu.hub.status.PipelineMetadataStore;
import msc.mawodu.hub.status.PipelineStatusController;
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

    @Bean
    @Autowired
    public MockPipelineDetailsProvider mockPipelineDetailsProvider(NotesStore notesDatabase, FilenamesStore filenamesDatabase) {
        return new MockPipelineDetailsProvider(notesDatabase, filenamesDatabase);
    }


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
