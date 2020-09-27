package msc.mawodu.hub.development;

import msc.mawodu.hub.pipelines.PipelineOverview;
import msc.mawodu.hub.status.PipelineOverviewDataProvider;

import java.util.ArrayList;
import java.util.List;

public class StubPipelineOverviewDataProvider implements PipelineOverviewDataProvider {

    public List<PipelineOverview> fetchAll() {
        List<PipelineOverview> pipelineOverviews = new ArrayList<>();
        pipelineOverviews.add(new PipelineOverview("RNASeQC", "running", 3, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"));
        pipelineOverviews.add(new PipelineOverview("SolexaQA", "running", 4, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"));
        pipelineOverviews.add(new PipelineOverview("FastqMcf", "running", 15, "Ut enim ad minim veniam, quis nostrud, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet"));

        return pipelineOverviews;
    }
}
