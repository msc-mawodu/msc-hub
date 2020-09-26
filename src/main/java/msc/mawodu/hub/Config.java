package msc.mawodu.hub;


import msc.mawodu.hub.mocks.MockInMemoryNotesDatabase;
import msc.mawodu.hub.notes.NotesUpdateController;
import msc.mawodu.hub.stubs.StubPipelineOverviewDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableScheduling
public class Config {

    @Bean
    MockInMemoryNotesDatabase mockInMemoryNotesDatabase() {
        return new MockInMemoryNotesDatabase();
    }

    @Bean
    StubPipelineOverviewDataProvider stubPipelineOverviewDataProvider() {
        return new StubPipelineOverviewDataProvider();
    }

    @Autowired
    NotesUpdateController notesUpdateController(NotesStore notesStore) {
        return new NotesUpdateController(notesStore);
    }

    @Autowired
    PipelineController pipelineController(PipelineDetailsDataProvider pipelineDetailsDataProvider) {
        return new PipelineController(pipelineDetailsDataProvider);
    }
}
