package msc.mawodu.hub.fileDownload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class MockFileResolver implements FileResolver {

    private static final Logger logger = LoggerFactory.getLogger(MockFileResolver.class);

    @Override
    public Optional<File> fetchFileForPipeline(String pipelineId, String fileName) {

        Resource resource = new ClassPathResource(fileName);
        InputStream input = null;
        try {
            return Optional.of(resource.getFile());
        } catch (IOException e) {
            logger.error("Unable to find the file.");
        }
        return Optional.empty();
    }
}