package fitnesstracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;
    private final RequestMatcher requestMatcher =
            new AntPathRequestMatcher("/api/tracker/**");
    private final AuthenticationEntryPoint authenticationEntryPoint = ((request,
                                                                        response,
                                                                        authException) -> {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(authException.getMessage());
    });

    public ApiKeyFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(requestMatcher.matches(request)) {
            try {
                var apiKey = request.getHeader("X-API-Key");
                if(apiKey != null) {
                    Authentication authentication = new ApiKeyAuthentication(apiKey);
                    authentication = authenticationManager.authenticate(authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                    return;
                }
                throw new BadCredentialsException("No api key was provided...");
            } catch (AuthenticationException e) {
                authenticationEntryPoint.commence(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
