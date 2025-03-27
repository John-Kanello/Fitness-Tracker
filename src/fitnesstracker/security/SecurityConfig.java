package fitnesstracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;
    private final FitnessAppUserDetailsService fitnessAppUserDetailsService;

    public SecurityConfig(ApiKeyAuthenticationProvider apiKeyAuthenticationProvider, FitnessAppUserDetailsService fitnessAppUserDetailsService) {
        this.apiKeyAuthenticationProvider = apiKeyAuthenticationProvider;
        this.fitnessAppUserDetailsService = fitnessAppUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(matcherRegistry -> matcherRegistry
                        .requestMatchers("/actuator/shutdown").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/api/developers/signup").permitAll()
                        .anyRequest().authenticated()
                )
                .with(new ApiKeyConfigurer(), Customizer.withDefaults())
                .authenticationProvider(apiKeyAuthenticationProvider)
                .userDetailsService(fitnessAppUserDetailsService)
                .sessionManagement(sessions -> sessions
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
