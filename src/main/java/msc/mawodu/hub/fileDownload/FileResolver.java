package msc.mawodu.hub.fileDownload;

import java.io.File;
import java.util.Optional;

public interface FileResolver {
    Optional<File> fetchFileForPipeline(String pipelineId, String fileName);
}
