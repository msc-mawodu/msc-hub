package msc.mawodu.hub.providers;

import msc.mawodu.hub.pipelines.PipelineOverview;

import java.util.List;

public interface PipelineOverviewDataProvider {
    List<PipelineOverview> fetchAll();
}
