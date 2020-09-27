package msc.mawodu.hub.files;

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
            logger.info(String.format("Successfully stored file at: %s", fullFilePath));
            return true;
        } catch (IOException e) {
            logger.error("Error saving file to filesystem");
            return false;
        }
    }

    @Override
    public boolean registerNewPipeline(String pipelineId) {
        String fullFolderPath = String.format("%s/%s", systemPath, pipelineId);
        File directory = new File(fullFolderPath);
        if (! directory.exists()){
            directory.mkdir();
            logger.info(String.format("New folder for pipeline: %s created.", pipelineId));
            return true;
        }
        logger.error(String.format("Folder for pipeline: %s already exists. This shouldn't be happening.", pipelineId));
        return false;

    }

    private void generateInitialDirectoryPath() {
        File directory = new File(systemPath);
        if (! directory.exists()){
            directory.mkdirs();
        }
    }
}
