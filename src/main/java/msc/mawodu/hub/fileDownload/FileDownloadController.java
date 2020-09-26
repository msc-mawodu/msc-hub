package msc.mawodu.hub.fileDownload;

import org.apache.maven.surefire.shade.org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileDownloadController {

    private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getFile(HttpServletResponse response) {

        // FileSystemResource
        Resource resource = new ClassPathResource("qc-report.txt");
        InputStream input = null;
        try {
            input = resource.getInputStream();
        } catch (IOException e) {
            logger.error("Unable to find the file.");
        }

        response.setHeader("Content-disposition", "attachment; filename=" + "STUB.txt");

        try {
            return IOUtils.toByteArray(input);
        } catch (IOException e) {
            logger.error("Error turning the file into byte array.");
        }

        return null;
    }
}
