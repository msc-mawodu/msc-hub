package msc.mawodu.hub.fileUpload;

import msc.mawodu.hub.Routes;
import msc.mawodu.hub.fileDownload.FileResolver;
import msc.mawodu.hub.fileDownload.FilenamesStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private final FileResolver fileResolver;
    private final FilenamesStore filenamesStore;

    @Autowired
    public FileUploadController(FileResolver fileResolver, FilenamesStore filenamesStore) {
        this.fileResolver = fileResolver;
        this.filenamesStore = filenamesStore;
    }

    @PostMapping(value = Routes.PIPELINE_FILE_UPLOAD)
    @ResponseStatus(HttpStatus.OK)
    public void handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String pipelineId) {
        logger.info(String.format("Received file to be uploaded: %s", file.getOriginalFilename()));

        boolean fileStoredSuccessfully = fileResolver.storeFile(file, pipelineId);
        if(fileStoredSuccessfully) {
            filenamesStore.registerNewFile(pipelineId, file.getOriginalFilename());
        }
    }

    @PostMapping(value = Routes.PIPELINE_MANUAL_FILE_UPLOAD)
    public String handleManualFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String pipelineId, RedirectAttributes redirectAttributes) {
        logger.info(String.format("Received manual file upload: %s", file.getOriginalFilename()));

        boolean fileStoredSuccessfully = fileResolver.storeFile(file, pipelineId);
        if(fileStoredSuccessfully) {
            filenamesStore.registerNewFile(pipelineId, file.getOriginalFilename());
            redirectAttributes.addFlashAttribute(Routes.PIPELINE_MANUAL_FILE_UPLOADED, String.format("uploaded %s", file.getOriginalFilename()));
            return String.format("redirect:/%s/%s", "pipeline", pipelineId);
        }

        return "";
    }
}