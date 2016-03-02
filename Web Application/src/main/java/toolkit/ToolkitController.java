package toolkit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by James Euesden on 01/03/2016.
 */
@RestController
public class ToolkitController {
    @RequestMapping("/")
    public String index() {
        QualityToolkit toolkit = new QualityToolkit();
        toolkit.run();
        return "Insert some toolkit stuff here!";
    }

}
