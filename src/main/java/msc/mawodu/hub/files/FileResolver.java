package msc.mawodu.hub.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

public interface FileResolver {
    Optional<File> fetchFileForPipeline(String pipelineId, String fileName);
    boolean storeFile(MultipartFile file, String pipelineId);
}
