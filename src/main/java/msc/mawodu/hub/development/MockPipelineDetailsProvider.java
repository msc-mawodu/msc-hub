package msc.mawodu.hub.development;

import msc.mawodu.hub.stores.NotesStore;
import msc.mawodu.hub.stores.FilenamesStore;
import msc.mawodu.hub.pipelines.PipelineDetails;
import msc.mawodu.hub.providers.PipelineDetailsDataProvider;
import msc.mawodu.hub.pipelines.PipelineOverview;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockPipelineDetailsProvider implements PipelineDetailsDataProvider {

    private NotesStore notesDatabase;
    private FilenamesStore filenamesDatabase;

    public MockPipelineDetailsProvider(NotesStore notesDatabase, FilenamesStore filenamesDatabase) {
        this.notesDatabase = notesDatabase;
        this.filenamesDatabase = filenamesDatabase;
    }

    private static final Map<String, PipelineOverview> mockDb;

    static {
        mockDb = new HashMap<>();
        mockDb.put("RNASeQC", new PipelineOverview("RNASeQC", "running", 3, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"));
        mockDb.put("SolexaQA", new PipelineOverview("SolexaQA", "running", 4, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"));
        mockDb.put("FastqMcf", new PipelineOverview("FastqMcf", "running", 15, "Ut enim ad minim veniam, quis nostrud, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet"));
    }

    @Override
    public PipelineDetails fetchById(String uid) {
        PipelineOverview overview = mockDb.getOrDefault(uid, null);
        if (null == overview) {
            return null;
        }

        String pipelineNotes = notesDatabase.fetchNotesByPipelineId(uid);
        List<String> pipelineFiles = filenamesDatabase.fetchAllFilenames(uid);

        return new PipelineDetails(overview, pipelineNotes, pipelineFiles);
    }

}
