package msc.mawodu.hub;


import msc.mawodu.hub.fileDownload.FileDownloadController;
import msc.mawodu.hub.fileDownload.FileResolver;
import msc.mawodu.hub.fileDownload.FilenamesStore;
import msc.mawodu.hub.fileDownload.FilesystemFileResolver;
import msc.mawodu.hub.fileUpload.FileUploadController;
import msc.mawodu.hub.mocks.MockInMemoryFileNamesDatabase;
import msc.mawodu.hub.mocks.MockInMemoryNotesDatabase;
import msc.mawodu.hub.notes.NotesUpdateController;
import msc.mawodu.hub.stubs.StubPipelineOverviewDataProvider;
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

    @Bean
    MockInMemoryNotesDatabase mockInMemoryNotesDatabase() {
        return new MockInMemoryNotesDatabase();
    }

    @Bean
    MockInMemoryFileNamesDatabase mockInMemoryFileNamesDatabase() {
        return new MockInMemoryFileNamesDatabase();
    }

    @Bean
    StubPipelineOverviewDataProvider stubPipelineOverviewDataProvider() {
        return new StubPipelineOverviewDataProvider();
    }

    @Bean
    FilesystemFileResolver filesystemFileResolver() {
        return new FilesystemFileResolver();
    }

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
}
