package msc.mawodu.hub.stubs;

import msc.mawodu.hub.PipelineDetails;
import msc.mawodu.hub.PipelineOverview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StubPipelineDetailsDataProvider {

    public static PipelineDetails fetchById(String uid) {
        PipelineOverview overview = mockDB().getOrDefault(uid, null);
        if (null == overview) {
            return null;
        }

        return new PipelineDetails(overview, mockNotes(), mockPaths());
    }

    private static Map<String, PipelineOverview> mockDB() {
        Map<String, PipelineOverview> mockDb = new HashMap<>();
        mockDb.put("a123", new PipelineOverview("a123", "running", 3, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud", "/pipeline/a123"));
        mockDb.put("bc23", new PipelineOverview("bc23", "running", 4, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud", "/pipeline/bc23"));
        mockDb.put("de3", new PipelineOverview("de3", "running", 15, "Ut enim ad minim veniam, quis nostrud, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet,Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet.Lorem ipsum dolor sit amet", "/pipeline/de3"));
        return mockDb;
    }

    private static String mockNotes() {
        return "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium," +
                " totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae " +
                "dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, " +
                "sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam " +
                "est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius " +
                "modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima " +
                "veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea " +
                "commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam " +
                "nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
    }

    private static List<String> mockPaths() {
        List<String> paths = new ArrayList<>();
        paths.add("/foo/bar/tar.txt");
        paths.add("/foo/bar/tar1.txt");
        paths.add("/foo/bar/tar2.txt");
        paths.add("/foo/bar/tar3.txt");
        return paths;
    }
}
