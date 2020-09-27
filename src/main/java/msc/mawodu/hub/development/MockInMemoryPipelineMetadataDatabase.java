package msc.mawodu.hub.development;

import msc.mawodu.hub.status.PipelineMetaData;
import msc.mawodu.hub.stores.PipelineMetadataStore;

import java.util.*;

public class MockInMemoryPipelineMetadataDatabase implements PipelineMetadataStore {

    private static Map<String, PipelineMetaData> mockDB;

    static {
        mockDB = new HashMap<>();
        mockDB.put("RNASeQC", new PipelineMetaData("RNASeQC", "RUNNING", "127.0.0.1","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"));
        mockDB.put("SolexaQA", new PipelineMetaData("SolexaQA", "RUNNING", "127.0.0.1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"));
        mockDB.put("FastqMcf", new PipelineMetaData("FastqMcf", "RUNNING", "127.0.0.1", "Ut enim ad minim veniam, quis nostrud, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet"));
    }

    @Override
    public Optional<PipelineMetaData> fetch(String pipelineId) {
        if (mockDB.containsKey(pipelineId)) {
            return Optional.of(mockDB.get(pipelineId));
        }
        return Optional.empty();
    }

    @Override
    public List<PipelineMetaData> fetchAll() {
        return new ArrayList<PipelineMetaData>(mockDB.values());
    }

    @Override
    public boolean update(PipelineMetaData pipelineMetaData) {
        String id = pipelineMetaData.getId();
        Optional<PipelineMetaData> storedMeda = fetch(id);
        if (storedMeda.isPresent()) {
            mockDB.put(id, pipelineMetaData);
            return true;
        }
        return false;
    }

    @Override
    public boolean registerNewPipeline(PipelineMetaData pipelineMetaData) {
        String id = pipelineMetaData.getId();
        mockDB.put(id, pipelineMetaData);
        return true;
    }
}
