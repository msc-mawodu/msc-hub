package msc.mawodu.hub.fileDownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Optional;

public class FilesystemFileResolver implements FileResolver {

    @Value("${files.storage.path}")
    private String systemPath;

    private static final Logger logger = LoggerFactory.getLogger(FilesystemFileResolver.class);

    @Override
    public Optional<File> fetchFileForPipeline(String pipelineId, String fileName) {
        String fullFilePath = String.format("%s/%s/%s", systemPath, pipelineId, fileName);
        logger.info(String.format("Requesting file: %s", fullFilePath));

        FileSystemResource fileSystemResource = new FileSystemResource(fullFilePath);
        if(fileSystemResource.exists()) {
            return Optional.of(fileSystemResource.getFile());
        }

        return Optional.empty();
    }
}
