package msc.mawodu.hub.status;

import java.util.List;
import java.util.Optional;

public interface PipelineMetadataStore {
    Optional<PipelineMetaData> fetch(String pipelineId);

    List<PipelineMetaData> fetchAll();

    boolean update(PipelineMetaData pipelineMetaData);

    boolean registerNewPipeline(PipelineMetaData pipelineMetaData);
}
