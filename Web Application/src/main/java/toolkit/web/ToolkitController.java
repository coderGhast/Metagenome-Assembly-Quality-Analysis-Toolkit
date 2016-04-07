package toolkit.web;

import org.apache.catalina.startup.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import toolkit.domain.*;
import toolkit.utilities.GraphDataBuilder;
import toolkit.utilities.UserContentValidator;

/**
 * Created by James Euesden on 01/03/2016.
 */
@Controller
public class ToolkitController {

    private UserContentValidator _validator;

    public ToolkitController(){
        _validator = new UserContentValidator();
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("userparameters", new UserParameters());
        return "welcome";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String dataResult(@ModelAttribute UserParameters params, Model model) {
        QualitySummary result;
        if(_validator.validateUserContent(params.getUserContent())){
            result = new QualityToolkit().runInput(params);
        } else {
            // TODO: Own generated files
            params.setFileName("./src/main/resources/static/contig.1274754.fa");
            result = new QualityToolkit().runFile(params);
        }

        model.addAttribute("gcResult", GraphDataBuilder.getGcChartData(result.getGcResults().get(0), params));
        model.addAttribute("orfResult", result.getOrfResults().get(0).getPotentialOrfLocations());
        model.addAttribute("userparameters", params);
        return "toolkit";
    }
}
