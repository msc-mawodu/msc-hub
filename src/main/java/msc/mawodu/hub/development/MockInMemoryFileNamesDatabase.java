package msc.mawodu.hub.development;

import msc.mawodu.hub.stores.FilenamesStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MockInMemoryFileNamesDatabase implements FilenamesStore {

    private static final Logger logger = LoggerFactory.getLogger(MockInMemoryFileNamesDatabase.class);

    private static Map<String, List<String>> mockDB;

    static {
        mockDB = new HashMap<>();
        mockDB.put("RNASeQC", new ArrayList<String>(Arrays.asList("qc.txt", "report.txt", "performance.txt")));
        mockDB.put("SolexaQA", new ArrayList<String>(Arrays.asList("qc.txt", "report.txt", "performance.txt")));
        mockDB.put("FastqMcf", new ArrayList<String>(Arrays.asList("qc.txt", "report.txt", "performance.txt")));
    }

    @Override
    public List<String> fetchAllFilenames(String pipelineId) {
        if (mockDB.containsKey(pipelineId)) {
            return mockDB.get(pipelineId);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean registerNewFile(String pipelineId, String fileName) {
        try {
            mockDB.get(pipelineId).add(fileName);
            logger.info("Stored new filename in db");
            return true;
        } catch (Exception e) {
            logger.error(String.format("Unable to stored new filename in db for: %s %s", pipelineId, fileName));
            return false;
        }
    }

}
