package msc.mawodu.hub.mocks;

import msc.mawodu.hub.PipelineOverview;

import java.util.ArrayList;
import java.util.List;

public class MockPipelineOverviewDataProvider {

    public static List<PipelineOverview> fetch() {
        List<PipelineOverview> pipelineOverviews = new ArrayList<>();
        pipelineOverviews.add(new PipelineOverview("a123", "running", 3, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud", "/pipeline/a123"));
        pipelineOverviews.add(new PipelineOverview("bc23", "running", 4, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud", "/pipeline/bc23"));
        pipelineOverviews.add(new PipelineOverview("de3", "running", 15, "Ut enim ad minim veniam, quis nostrud, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet", "/pipeline/de3"));

        return pipelineOverviews;
    }
}
