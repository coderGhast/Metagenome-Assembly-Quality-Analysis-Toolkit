package toolkit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import toolkit.domain.GcResult;
import toolkit.domain.QualityToolkit;
import toolkit.domain.GcResultViewData;
import toolkit.domain.UserParameters;
import toolkit.utilities.GraphDataBuilder;

/**
 * Created by James Euesden on 01/03/2016.
 */
@Controller
public class ToolkitController {

    @RequestMapping("/")
    public String index(Model model) {
        // For Prototype testing
        UserParameters params = new UserParameters();
        params.awayFromAverageThreshold = 2.5;
        params.fileName = "./src/main/resources/static/contig.1274754.fa";
        params.gcWindowSize = 1000;

        GcResult result = new QualityToolkit().run(params.fileName, params.gcWindowSize);
        model.addAttribute("gcResult", new GraphDataBuilder().getGcChartData(result, params));

        return "toolkit";
    }
}
