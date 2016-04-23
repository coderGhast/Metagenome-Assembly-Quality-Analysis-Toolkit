package toolkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


/**
 * Created by James Euesden on 26/02/2016.
 *
 * Main method for running the metagenome report/inspection/quality assessment tool 'Khimeta'.
 * Starts running a SpringApplication web service, defined in the pom.xml file.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
    }
}
