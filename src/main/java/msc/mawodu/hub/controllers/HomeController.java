package msc.mawodu.hub.controllers;

import msc.mawodu.hub.pipelines.PipelineOverview;
import msc.mawodu.hub.providers.PipelineOverviewDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private PipelineOverviewDataProvider overviewDataProvider;

    @Autowired
    public HomeController(PipelineOverviewDataProvider overviewDataProvider) {
        this.overviewDataProvider = overviewDataProvider;
    }

    @GetMapping(value="/")
    public ModelAndView displayArticle(Map<String, Object> model) {
        List<PipelineOverview> pipelines = overviewDataProvider.fetchAll();
        model.put("pipelines", pipelines);
        return new ModelAndView("home", model);
    }

}
