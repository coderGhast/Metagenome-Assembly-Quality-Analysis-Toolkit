package toolkit.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import toolkit.domain.*;
import toolkit.utilities.GraphDataBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by James Euesden on 01/03/2016.
 *
 * The controller class for the web service of the toolkit application.
 * Handles incoming requests and what is expected within a users session in order to progress. Using @SessionAttributes
 * allows the application to also know whether a user has permission to be on a particular page or not. For example, if
 * a user tries to skip to the 'toolkit' results page without having submitted any data, they will be presented with the
 * error page, as they do not have any @SessionAttributes of submitted data.
 */
@SessionAttributes({ "userParameters", "contiglist", "discardedcontigcount", "gcResult", "orfResult", "contiguousread"})
@Controller
public class ToolkitController {
    /**
     * Returns the welcome page to the user, setting up some blank @SessionAttributes to be carried with them for the
     * duration of their session and populated as they progress through the toolkit.
     * @param model The Model attribute, carrying the users data as they browse the web service.
     * @return The page to be returned, where the String matches a template file in the resources directory.
     */
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("userParameters", new UserParameters());
        model.addAttribute("contiguousread", new ContiguousRead());
        return "welcome";
    }

    /**
     * On a GET to the list of contiguous reads, a user may expect to see any current contiguous reads in the list
     * from their session, as they are assumed to have already submitted data and hit the POST endpoint first and then
     * left that page and are now returning to see the data they have in their session.
     * @param userParameters The user parameters in their session.
     * @param model The Model attribute, carrying the users data as they browse the web service.
     * @return The page to be returned, where the String matches a template file in the resources directory.
     */
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public String viewPreviousUserInput(@Valid @ModelAttribute(value = "userParameters") UserParameters userParameters,
                                        BindingResult bindingResult,
                                        Model model){
        if (bindingResult.hasErrors()) {
            return "welcome";
        }

        return "list";
    }


    /**
     * Upon submission of the users data on the 'welcome' page, a POST request is made to /list, that calls
     * to this method where the data is used to create a list of ContiguousReads that can be displayed back to the
     * user, after removing any that fall under their requested threshold.
     * @param userParameters The user parameters in their session.
     * @param model The Model attribute, carrying the users data as they browse the web service.
     * @return The page to be returned, where the String matches a template file in the resources directory.
     */
    @RequestMapping(value="/list", method = RequestMethod.POST)
    public String readUserInput(@Valid UserParameters userParameters,
                                BindingResult bindingResult,
                                Model model){
        if (bindingResult.hasErrors()) {
            return "welcome";
        }

        FastaReader reader = new FastaReader();
        try{
            ContigResult contigResult = reader.readSequenceInput(userParameters);
            ArrayList<ContiguousRead> contigList = contigResult.getContigList();

            model.addAttribute("contiglist", contigList);
            model.addAttribute("discardedcontigcount", contigResult.getDiscardedContigCount());
            model.addAttribute("contiguousread", new ContiguousRead());
        } catch(Exception e){
            bindingResult.addError(new ObjectError("UserParameters", "Your data is invalid, please check your data is valid and" +
                    " try inputting your data again"));
            return "welcome";
        }

        return "list";
    }

    /**
     * Displays the inspection results on an individual ContiguousRead, as selected from the '/list' view, and now
     * carried in the users @SessionAttributes. When the POST to this /result page is made, the inspection is carried
     * out by the application and then the results are added to the Model to be used by Thymeleaf in displaying as
     * part of the View.
     * @param userParameters The user parameters in their session.
     * @param contig The ContiguousRead that the user wishes to inspect further and have the application process.
     * @param model The Model attribute, carrying the users data as they browse the web service.
     * @return The page to be returned, where the String matches a template file in the resources directory.
     */
    @RequestMapping(value="/result", method = RequestMethod.POST)
    public String dataResult(@ModelAttribute(value = "userParameters") UserParameters userParameters,
                             @Valid @ModelAttribute(value = "contiguousread") ContiguousRead contig,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return "list";
        }

        QualitySummary result = QualityToolkit.qualityAssess(contig);

        model.addAttribute("contiguousread", contig);
        model.addAttribute("gcResult", GraphDataBuilder.getGcChartData(result.getGcResults(), contig.getAwayFromMeanThreshold()));
        Collections.sort(result.getOrfResults().getPotentialOrfLocations());
        model.addAttribute("orfResult", result.getOrfResults().getPotentialOrfLocations());
        return "toolkit";
    }
}
