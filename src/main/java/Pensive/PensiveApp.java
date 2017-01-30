package Pensive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class PensiveApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PensiveApp.class, args);
    }
}
