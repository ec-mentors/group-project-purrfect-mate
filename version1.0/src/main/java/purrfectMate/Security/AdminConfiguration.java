package purrfectMate.Security;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import purrfectMate.data.dto.RegisterDTO;
import purrfectMate.service.UserService;

@Configuration
public class AdminConfiguration {

    private final Logger logger;

    private final String adminPassword;

    private final String adminName;

    private final UserService userService;

    public AdminConfiguration(Logger logger, String adminPassword, String adminName, UserService userService) {
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.userService = userService;
        this.logger = logger;
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {

                if (!userService.usernameExists(adminName)) {
                    // Check if the user already exists
                    RegisterDTO registerDTO = new RegisterDTO(adminName, adminName + "@admin.purr", adminPassword);
                    userService.createAdmin(registerDTO);

                } else {
                    logger.debug("Admin user {} already exists. Aborting creation process. ", adminName);
                }
        };

    }
}