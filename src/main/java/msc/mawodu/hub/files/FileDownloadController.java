package msc.mawodu.hub.files;

import msc.mawodu.hub.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.util.Optional;

@RestController
public class FileDownloadController {

    private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

    private FileResolver fileResolver;

    @Autowired
    public FileDownloadController(FileResolver fileResolver) {
        this.fileResolver = fileResolver;
    }

    @GetMapping(value = Routes.PIPELINE_FILE_DOWNLOAD, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] download(@PathVariable String pipelineId, @PathVariable  String fileName, HttpServletResponse response) throws FileSystemException {
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        Optional<File> file = fileResolver.fetchFileForPipeline(pipelineId, fileName);

        if(!file.isPresent()) {
            logger.error("Requested file does not exist.");
            throw new FileSystemException("No such file");
        }

        try {
            return Files.readAllBytes(file.get().toPath());
        } catch (IOException e) {
            logger.error("Error while turning file into byte array.");
        }

        return null;
    }
}
