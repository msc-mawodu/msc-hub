package msc.mawodu.hub.status;

import msc.mawodu.hub.files.FilenamesStore;
import msc.mawodu.hub.pipelines.PipelineOverview;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BasePipelineOverviewDataProvider implements PipelineOverviewDataProvider {

    private PipelineMetadataStore metadataStore;
    private FilenamesStore filenamesStore;

    @Autowired
    public BasePipelineOverviewDataProvider(PipelineMetadataStore metadataStore, FilenamesStore filenamesStore) {
        this.metadataStore = metadataStore;
        this.filenamesStore = filenamesStore;
    }

    @Override
    public List<PipelineOverview> fetchAll() {
        List<PipelineOverview> overviews = new ArrayList<>();
        List<PipelineMetaData> pipelinesMetaData = metadataStore.fetchAll();
        for (PipelineMetaData metaData : pipelinesMetaData) {
            int filesCount = filenamesStore.fetchAllFilenames(metaData.getId()).size();
            overviews.add(new PipelineOverview(metaData.getId(), metaData.getState(), filesCount, metaData.getDescription()));
        }
        return overviews;
    }
}
