package msc.mawodu.hub.controllers;

import msc.mawodu.hub.Routes;
import msc.mawodu.hub.files.FileResolver;
import msc.mawodu.hub.status.PipelineMetaData;
import msc.mawodu.hub.stores.PipelineMetadataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PipelineStatusController {

    private static final Logger logger = LoggerFactory.getLogger(PipelineStatusController.class);

    private PipelineMetadataStore metadataStore;
    private FileResolver fileResolver;

    @Autowired
    public PipelineStatusController(PipelineMetadataStore metadataStore, FileResolver fileResolver) {
        this.metadataStore = metadataStore;
        this.fileResolver = fileResolver;
    }

    @PostMapping(value = Routes.PIPELINE_STATUS)
    @ResponseStatus(HttpStatus.OK)
    public void recordStatus(@RequestBody PipelineMetaData pipelineMetaData) {

        String pipelineId = pipelineMetaData.getId();

        Optional<PipelineMetaData> storedPipelineMetaData = metadataStore.fetch(pipelineId);
        if (storedPipelineMetaData.isPresent()) {
            metadataStore.update(pipelineMetaData);
            logger.info(String.format("Received status update for pipeline: %s", pipelineId));
        } else {
            fileResolver.registerNewPipeline(pipelineId);
            metadataStore.registerNewPipeline(pipelineMetaData);
            logger.info(String.format("Fully registered new pipeline: %s", pipelineId));
        }


    }
}
