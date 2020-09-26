package msc.mawodu.hub.mocks;

import msc.mawodu.hub.NotesStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MockInMemoryNotesDatabase implements NotesStore {

    private static Map<String, String> mockDB;

    static {
        mockDB = new HashMap<>();
        mockDB.put("RNASeQC", "Basic Starting notes for pipeline RNASeQC");
        mockDB.put("SolexaQA", "Basic Starting notes for pipeline SolexaQA");
        mockDB.put("FastqMcf", "Basic Starting notes for pipeline FastqMcf");
    }

    @Override
    public String fetchNotesByPipelineId(String pipelineId) {
        return mockDB.get(pipelineId);
    }

    @Override
    public boolean updatePipelineNotes(String pipelineId, String updatedNotes) {
        try {
            mockDB.put(pipelineId, updatedNotes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
