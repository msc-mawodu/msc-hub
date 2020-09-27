package msc.mawodu.hub.status;

import java.util.Optional;

public interface PipelineMetadataStore {
    Optional<PipelineMetaData> fetch(String pipelineId);

    boolean update(PipelineMetaData pipelineMetaData);

    boolean registerNewPipeline(PipelineMetaData pipelineMetaData);
}
