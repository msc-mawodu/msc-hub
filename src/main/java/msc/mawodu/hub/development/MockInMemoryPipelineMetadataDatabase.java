package msc.mawodu.hub.development;

import msc.mawodu.hub.status.PipelineMetaData;
import msc.mawodu.hub.stores.PipelineMetadataStore;

import java.util.*;

public class MockInMemoryPipelineMetadataDatabase implements PipelineMetadataStore {

    private static Map<String, PipelineMetaData> mockDB;

    static {
        mockDB = new HashMap<>();
        mockDB.put("RNASeQC", new PipelineMetaData("RNASeQC", "Running", "127.0.0.1","Quality control checks on raw sequence data"));
        mockDB.put("SolexaQA", new PipelineMetaData("SolexaQA", "Running", "127.0.0.1", "Sequence quality statistics visualisation for NGS data."));
        mockDB.put("FastqMcf", new PipelineMetaData("FastqMcf", "Running", "127.0.0.1", "Scans for adapters and performs clipping."));
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
