package msc.mawodu.hub.pipelines;

import java.util.List;

public interface PipelineOverviewDataProvider {
    List<PipelineOverview> fetchAll();
}
