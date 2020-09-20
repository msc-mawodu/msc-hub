package msc.mawodu.hub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
public class HomeController {

    @Autowired
    public HomeController() {
    }

    @GetMapping(value="/")
    public ModelAndView displayArticle(Map<String, Object> model) {
        model.put("test", "123321123");
        return new ModelAndView("home", model);
    }

}
