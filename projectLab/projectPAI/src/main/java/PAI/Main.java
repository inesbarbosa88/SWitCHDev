package PAI;

import PAI.controller.US02_ConfigureAccessMethodController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        // Get the controller bean from the Spring context
        US02_ConfigureAccessMethodController controller = context.getBean(US02_ConfigureAccessMethodController.class);

        // Call the method
        String methodName1 = "TestMethod1";
        boolean result = controller.configureAccessMethod(methodName1);

        System.out.println("Result: " + result);
    }
}