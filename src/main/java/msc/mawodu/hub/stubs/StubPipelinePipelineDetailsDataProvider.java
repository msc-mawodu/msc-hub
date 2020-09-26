package msc.mawodu.hub.stubs;

import msc.mawodu.hub.PipelineDetailsDataProvider;
import msc.mawodu.hub.PipelineDetails;
import msc.mawodu.hub.PipelineOverview;
import msc.mawodu.hub.mocks.MockInMemoryNotesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StubPipelinePipelineDetailsDataProvider implements PipelineDetailsDataProvider {

    @Autowired
    MockInMemoryNotesDatabase notesDatabase;

    @Override
    public PipelineDetails fetchById(String uid) {
        PipelineOverview overview = mockDB().getOrDefault(uid, null);
        if (null == overview) {
            return null;
        }

        String pipelineNotes = notesDatabase.fetchNotesByPipelineId(uid);

        return new PipelineDetails(overview, pipelineNotes, mockFilenames());
    }

    private static Map<String, PipelineOverview> mockDB() {
        Map<String, PipelineOverview> mockDb = new HashMap<>();
        mockDb.put("RNASeQC", new PipelineOverview("RNASeQC", "running", 3, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"));
        mockDb.put("SolexaQA", new PipelineOverview("SolexaQA", "running", 4, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"));
        mockDb.put("FastqMcf", new PipelineOverview("FastqMcf", "running", 15, "Ut enim ad minim veniam, quis nostrud, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet"));
        return mockDb;
    }

    private static List<String> mockFilenames() {
        List<String> paths = new ArrayList<>();
        paths.add("qc.txt");
        paths.add("report.txt");
        paths.add("performance.txt");
        return paths;
    }
}
