package toolkit.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by James Euesden on 23/04/2016.
 *
 * Handles the exceptions that come from the Controller. With the applications current functionality, there is
 * no direct need to respond to the user with anything more than the error page indicating an issue and the potential
 * reason that they forgot to submit their data. The Exception bubble to the top and are output to the console, and
 * for now this is enough, instead of burying the Exception below.
 *
 * Code for this class modified from: http://stackoverflow.com/questions/28501154/spring-boot-error-handling
 */
@Controller
public class BasicErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";


    @RequestMapping(value=ERROR_PATH)
    @ExceptionHandler(value = {Exception.class})
    public String defaultErrorHandler(Model model, HttpServletRequest request, Exception e) {

        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
