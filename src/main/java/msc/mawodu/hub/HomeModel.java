package msc.mawodu.hub;

import msc.mawodu.hub.pipelines.PipelineOverview;

import java.util.List;

public class HomeModel {
    private String title;
    private List<PipelineOverview> currentPipelines;
    private List<PipelineOverview> completedPipelines;

    public HomeModel(String title, List<PipelineOverview> currentPipelines, List<PipelineOverview> completedPipelines) {
        this.title = title;
        this.currentPipelines = currentPipelines;
        this.completedPipelines = completedPipelines;
    }
}
