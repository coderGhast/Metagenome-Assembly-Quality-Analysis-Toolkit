package toolkit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import toolkit.domain.GcResult;
import toolkit.domain.QualityToolkit;

/**
 * Created by James Euesden on 01/03/2016.
 */
@Controller
public class ToolkitController {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelMap modelMap = new ModelMap();
        QualityToolkit toolkit = new QualityToolkit();
        GcResult result = toolkit.run();
        modelMap.addAttribute(result);
        return new ModelAndView("toolkit", modelMap);
    }
}
