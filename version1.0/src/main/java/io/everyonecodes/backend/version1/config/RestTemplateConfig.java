package io.everyonecodes.backend.version1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {


    @Value("${spring.datasource.username}") String freesqlUsername;
    @Value("${spring.datasource.password}") String freesqlPassword;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .basicAuthentication(freesqlUsername, freesqlPassword)
                .build();
    }
}
