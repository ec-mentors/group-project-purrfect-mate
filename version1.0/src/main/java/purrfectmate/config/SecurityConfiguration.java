package purrfectmate.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import purrfectmate.data.repository.HumanRepository;


import static org.springframework.security.config.Customizer.withDefaults;

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
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/humans", "/home", "/catProfile", "/api/registration", "/register", "/api/login", "/login").permitAll()
                        .requestMatchers("/cats").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")  // URL of custom login page
                        .permitAll()  // Allow everyone to see the login page
                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(HumanRepository humanRepository) {

        return username -> humanRepository.findByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException(username));
    }


    // in-memory security configuration for admin
//    @Bean
//    public UserDetailsService adminDetailsService() {
//        UserDetails admin = User.withUsername(adminUsername)
//                .password(passwordEncoder().encode(adminPassword))
//                .authorities(adminAuthorities)
//                .build();
//
//        return new InMemoryUserDetailsManager(admin);
//    }

//    @Bean
//    public DaoAuthenticationProvider adminAuthenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(adminDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }

    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, HumanRepository humanRepository) throws Exception {
//        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
//
//        // Register custom authentication providers
//        auth.authenticationProvider(adminAuthenticationProvider());
//        auth.authenticationProvider(userAuthenticationProvider(userDetailsService(humanRepository)));
//
//        return auth.build();
//    }
}
