package toolkit.web;

import org.apache.catalina.startup.Tool;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import toolkit.domain.*;
import toolkit.utilities.GraphDataBuilder;
import toolkit.utilities.UserContentValidator;

import java.util.ArrayList;

/**
 * Created by James Euesden on 01/03/2016.
 */
@SessionAttributes({ "userparameters", "contiglist", "gcResult", "orfResult", "contiguousread"})
@Controller
public class ToolkitController {
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("userparameters", new UserParameters());
        return "welcome";
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String viewPreviousUserInput(@ModelAttribute(value = "userparameters") UserParameters params, Model model){
        model.addAttribute("contiguousread", new ContiguousRead());
        model.addAttribute("userparameters", params);
        return "contigs";
    }


    @RequestMapping(value="/list", method = RequestMethod.POST)
    public String readUserInput(@ModelAttribute(value = "userparameters")  UserParameters params, Model model){
        FastaReader reader = new FastaReader();
        ArrayList<ContiguousRead> contigList = reader.readSequenceInput(params);

        model.addAttribute("contiglist", contigList);
        model.addAttribute("contiguousread", new ContiguousRead());
        model.addAttribute("userparameters", params);
        return "contigs";
    }

    @RequestMapping(value="/result", method = RequestMethod.POST)
    public String dataResult(@ModelAttribute(value = "userparameters") UserParameters params,
                             ContiguousRead contig,
                              Model model) {
        QualitySummary result = QualityToolkit.qualityAssess(contig, params.getGcWindowSize(), params.getOrfLengthThreshold());

        model.addAttribute("contiguousread", contig);
        model.addAttribute("gcResult", GraphDataBuilder.getGcChartData(result.getGcResults().get(0), params));
        model.addAttribute("orfResult", result.getOrfResults().get(0).getPotentialOrfLocations());
        model.addAttribute("userparameters", params);
        return "toolkit";
    }
}
