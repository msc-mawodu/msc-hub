package msc.mawodu.hub.stores;

import java.util.List;

public interface FilenamesStore {
    List<String> fetchAllFilenames(String pipelineId);

    boolean registerNewFile(String pipelineId, String fileName);
}
