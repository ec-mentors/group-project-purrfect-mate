package purrfectMate.Security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import purrfectMate.data.repository.UserRepository;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Value("${credentials.admin.username}")
    private String adminUsername;

    @Value("${credentials.admin.password}")
    private String adminPassword;

    @Value("${credentials.admin.authorities}")
    private String adminAuthorities;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/humans", "/home", "/catProfile", "/api/registration", "/register", "api/login", "/login").permitAll()
                        .requestMatchers("/frontend/**").permitAll()  // Allow access to static resources
                        .requestMatchers("/cats").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint((request, response, authException) -> {
                    // Prevents the basic auth prompt by setting a custom response
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized Access");
                }));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository humanRepository, PasswordEncoder passwordEncoder) {
        // Define the in-memory admin user details
        UserDetails admin = User.withUsername(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .authorities(adminAuthorities)
                .build();

        // Create an instance of InMemoryUserDetailsManager for the admin user
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(admin);

        return username -> {
            // First, try to load the admin user from the in-memory manager
            try {
                UserDetails inMemoryUser = inMemoryUserDetailsManager.loadUserByUsername(username);
                if (inMemoryUser != null) {
                    return inMemoryUser;
                }
            } catch (UsernameNotFoundException e) {
                // Ignore and proceed to check the database
            }

            // If not found in memory, try to find the user in the HumanRepository
            return humanRepository.findByUsername(username)
                    .map(UserPrincipal::new)
                    .orElseThrow(() -> new UsernameNotFoundException(username));
        };
    }

    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}
