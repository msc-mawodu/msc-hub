package msc.mawodu.hub.providers;

import msc.mawodu.hub.pipelines.PipelineDetails;

public interface PipelineDetailsDataProvider {
    PipelineDetails fetchById(String uid);
}
