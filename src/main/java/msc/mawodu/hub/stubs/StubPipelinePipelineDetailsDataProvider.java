package msc.mawodu.hub.stubs;

import msc.mawodu.hub.PipelineDetails;
import msc.mawodu.hub.PipelineDetailsDataProvider;
import msc.mawodu.hub.PipelineOverview;
import msc.mawodu.hub.mocks.MockInMemoryFileNamesDatabase;
import msc.mawodu.hub.mocks.MockInMemoryNotesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StubPipelinePipelineDetailsDataProvider implements PipelineDetailsDataProvider {

    @Autowired
    MockInMemoryNotesDatabase notesDatabase;

    @Autowired
    MockInMemoryFileNamesDatabase filenamesDatabase;

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
