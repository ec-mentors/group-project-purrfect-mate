package purrfectMate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvironmentConfiguration {

    @Bean
    public String adminName() {
        return System.getenv("PURR_NAME");
    }

    @Bean
    public String adminPassword() {
        return System.getenv("PURR_PASSWORD");
    }
}
