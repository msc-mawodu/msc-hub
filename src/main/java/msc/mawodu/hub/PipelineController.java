package msc.mawodu.hub;

import msc.mawodu.hub.stubs.StubPipelineDetailsDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class PipelineController {

    @Autowired
    StubPipelineDetailsDataProvider pipelineDetailsDataProvider;

    @GetMapping(value="/pipeline/{uid}")
    public ModelAndView pipelinePage(@PathVariable final String uid, Map<String, Object> model) {
        PipelineDetails pipelineDetails = pipelineDetailsDataProvider.fetchById(uid);
        model.put("pipelineDetails", pipelineDetails);
        return new ModelAndView("pipeline", model);
    }

}
