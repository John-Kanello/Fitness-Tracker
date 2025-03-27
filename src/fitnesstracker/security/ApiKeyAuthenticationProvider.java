package fitnesstracker.security;

import fitnesstracker.service.UserApplicationService;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    private final UserApplicationService userApplicationService;

    public ApiKeyAuthenticationProvider(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Transactional(dontRollbackOn = BadCredentialsException.class)
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = authentication.getCredentials().toString();
        if(!userApplicationService.existsByApiKey(apiKey)) {
            throw new BadCredentialsException("Invalid api key...");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}
