package purrfectMate.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.InjectionPoint;

@Configuration
public class LoggerConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Create a new logger for each class injecting one
    public Logger logger(InjectionPoint injectionPoint) {

        // Determine the class that the logger is being injected into
        Class<?> targetClass = injectionPoint.getMember().getDeclaringClass();

        return LoggerFactory.getLogger(targetClass);
    }
}
