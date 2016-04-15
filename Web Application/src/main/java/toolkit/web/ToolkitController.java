package toolkit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toolkit.domain.*;
import toolkit.utilities.GraphDataBuilder;

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
        model.addAttribute("contiguousread", new ContiguousRead());
        return "welcome";
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String viewPreviousUserInput(@ModelAttribute(value = "userparameters") UserParameters params, Model model){
        return "list";
    }


    @RequestMapping(value="/list", method = RequestMethod.POST)
    public String readUserInput(@ModelAttribute(value = "userparameters")  UserParameters params, Model model){
        FastaReader reader = new FastaReader();
        ArrayList<ContiguousRead> contigList = reader.readSequenceInput(params);

        model.addAttribute("contiglist", contigList);
        model.addAttribute("contiguousread", new ContiguousRead());
        return "list";
    }

    @RequestMapping(value="/result", method = RequestMethod.POST)
    public String dataResult(@ModelAttribute(value = "userparameters") UserParameters params,
                             ContiguousRead contig,
                             Model model) {
        QualitySummary result = QualityToolkit.qualityAssess(contig);

        model.addAttribute("contiguousread", contig);
        model.addAttribute("gcResult", GraphDataBuilder.getGcChartData(result.getGcResults().get(0), contig.getAwayFromAverageThreshold()));
        model.addAttribute("orfResult", result.getOrfResults().get(0).getPotentialOrfLocations());
        return "toolkit";
    }
}
