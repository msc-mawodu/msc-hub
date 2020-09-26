package msc.mawodu.hub.fileDownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FilesystemFileResolver implements FileResolver {

    private static final Logger logger = LoggerFactory.getLogger(FilesystemFileResolver.class);

    @Value("${files.storage.path}")
    private String systemPath;

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

    @Override
    public boolean storeFile(MultipartFile file, String pipelineId) {
        String fileName = file.getOriginalFilename();
        String fullFilePath = String.format("%s/%s/%s", systemPath, pipelineId, fileName);
        try {
            file.transferTo(new File(fullFilePath));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
