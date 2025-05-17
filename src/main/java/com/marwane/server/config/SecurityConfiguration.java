package com.marwane.server.config;

import com.marwane.server.service.auth.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationService userDetailsService, PasswordEncoder passwordEncoder) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        http.authorizeHttpRequests(configurer -> {
            configurer
                    .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()

                    .requestMatchers(HttpMethod.GET, "/api/comptes/**").hasAnyAuthority("CUSTOMER", "AGENT")
                    .requestMatchers(HttpMethod.POST, "/api/comptes/**").hasAnyAuthority("CUSTOMER","AGENT")
                    .requestMatchers(HttpMethod.PUT, "/api/comptes/**").hasAnyAuthority("CUSTOMER","AGENT")
                    .requestMatchers(HttpMethod.DELETE, "/api/comptes/**").hasAnyAuthority("CUSTOMER","AGENT")
                    .requestMatchers(HttpMethod.PATCH, "/api/comptes/**").hasAnyAuthority("CUSTOMER","AGENT")

                    .requestMatchers(HttpMethod.GET, "/api/clients/**").hasAnyAuthority("CUSTOMER", "AGENT")
                    .requestMatchers(HttpMethod.POST, "/api/clients").hasAnyAuthority("AGENT")
                    .requestMatchers(HttpMethod.PUT, "/api/clients").hasAnyAuthority("AGENT")
                    .requestMatchers(HttpMethod.DELETE, "/api/clients/**").hasAnyAuthority("AGENT")
                    .requestMatchers(HttpMethod.PATCH, "/api/clients/**").hasAnyAuthority("AGENT")

                    .requestMatchers(HttpMethod.GET, "/api/operations/**").hasAnyAuthority("CUSTOMER", "AGENT")
                    .requestMatchers(HttpMethod.POST, "/api/operations/**").hasAnyAuthority("CUSTOMER","AGENT")
                    .requestMatchers(HttpMethod.PUT, "/api/operations/**").hasAnyAuthority("CUSTOMER","AGENT")
                    .requestMatchers(HttpMethod.DELETE, "/api/operations/**").hasAnyAuthority("CUSTOMER","AGENT")
                    .requestMatchers(HttpMethod.PATCH, "/api/operations/**").hasAnyAuthority("CUSTOMER","AGENT")
            ;
        });





        http.sessionManagement(sessionManagement -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.csrf(AbstractHttpConfigurer::disable);


        http.cors(cors -> {
            cors.configurationSource(corsConfigurationSource());});

        return http.build();
    }


    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration frontendConfig = new CorsConfiguration();
        //TODO: EDIT THE FRONTEND ORIGIN
        frontendConfig.addAllowedOrigin("http://localhost:5173");
        frontendConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        frontendConfig.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        frontendConfig.setAllowCredentials(true);

        CorsConfiguration subscriberConfig = new CorsConfiguration();
        subscriberConfig.addAllowedOrigin(CorsConfiguration.ALL);
        subscriberConfig.addAllowedMethod("POST");
        subscriberConfig.addAllowedHeader("Content-Type");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", frontendConfig);

        return source;
}



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

}