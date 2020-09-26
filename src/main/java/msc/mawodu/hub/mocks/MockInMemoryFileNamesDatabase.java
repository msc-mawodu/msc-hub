package msc.mawodu.hub.mocks;

import msc.mawodu.hub.fileDownload.FilenamesStore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockInMemoryFileNamesDatabase implements FilenamesStore {

    private static Map<String, List<String>> mockDB;

    static {
        mockDB = new HashMap<>();
        mockDB.put("RNASeQC", Arrays.asList("qc.txt", "report.txt", "performance.txt"));
        mockDB.put("SolexaQA", Arrays.asList("qc.txt", "report.txt", "performance.txt"));
        mockDB.put("FastqMcf", Arrays.asList("qc.txt", "report.txt", "performance.txt"));
    }

    @Override
    public List<String> fetchAllFilenames(String pipelineId) {
        return mockDB.get(pipelineId);
    }

    @Override
    public boolean registerNewFile(String pipelineId, String fileName) {
        try {
            mockDB.get(pipelineId).add(fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
