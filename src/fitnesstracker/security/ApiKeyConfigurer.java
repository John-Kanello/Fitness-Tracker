package fitnesstracker.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class ApiKeyConfigurer extends AbstractHttpConfigurer<ApiKeyConfigurer, HttpSecurity> {

    @Override
    public void configure(HttpSecurity builder) {
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        builder.addFilterAfter(
                new ApiKeyFilter(authenticationManager),
                UsernamePasswordAuthenticationFilter.class
        );
    }
}
