package toolkit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import toolkit.domain.GcResult;
import toolkit.domain.GcWindow;
import toolkit.domain.QualityToolkit;

import java.util.ArrayList;

/**
 * Created by James Euesden on 01/03/2016.
 */
@Controller
public class ToolkitController {
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("toolkit");
        QualityToolkit toolkit = new QualityToolkit();
        GcResult result = toolkit.run();
        ArrayList<Double> windows = result.getGCContentPercentages();
        StringBuilder windowNumbers = new StringBuilder();
        int i = 1;
        for(Double window : windows){
            if(i>1){
                windowNumbers.append(", ");
            }
            windowNumbers.append(i);
            i++;
        }

        modelAndView.addObject("gcresults", windows);
        modelAndView.addObject("windownumbers", windowNumbers.toString());

        return modelAndView;
    }
}
