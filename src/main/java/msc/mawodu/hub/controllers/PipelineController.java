package msc.mawodu.hub.controllers;

import msc.mawodu.hub.Routes;
import msc.mawodu.hub.pipelines.PipelineDetails;
import msc.mawodu.hub.providers.PipelineDetailsDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class PipelineController {

    private static final Logger logger = LoggerFactory.getLogger(PipelineController.class);

    private PipelineDetailsDataProvider pipelineDetailsDataProvider;

    public PipelineController(PipelineDetailsDataProvider pipelineDetailsDataProvider) {
        this.pipelineDetailsDataProvider = pipelineDetailsDataProvider;
    }

    @GetMapping(value="/pipeline/{uid}")
    public ModelAndView pipelinePage(@PathVariable final String uid, Map<String, Object> model, RedirectAttributes redirectAttributes) {
        PipelineDetails pipelineDetails = pipelineDetailsDataProvider.fetchById(uid);
        model.put("pipelineDetails", pipelineDetails);
        if(redirectAttributes.getFlashAttributes().containsKey(Routes.PIPELINE_MANUAL_FILE_UPLOADED)) {
            logger.info("Redirected from manual file upload");
            Object uploadSuccessfull = redirectAttributes.getFlashAttributes().get(Routes.PIPELINE_MANUAL_FILE_UPLOADED);
            model.put("uploadRedirect", uploadSuccessfull);
        }

        return new ModelAndView("pipeline", model);
    }

}
