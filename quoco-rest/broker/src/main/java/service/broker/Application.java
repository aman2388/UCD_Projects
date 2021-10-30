package service.broker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Application {
    //Store host args for each services
    public static ArrayList<String> hostList = new ArrayList<>();

    //Inject the host args into the LocalBrokerService RestController
    @Bean
    public ArrayList<String> hosts() {
        return hostList;
    }

    public static void main(String[] args) {
        hostList.addAll(Arrays.asList(args));
        SpringApplication.run(Application.class, args);
    }
}
