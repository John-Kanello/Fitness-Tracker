package fitnesstracker.security;

import fitnesstracker.service.FitnessAppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FitnessAppUserDetailsService implements UserDetailsService {
    private final FitnessAppUserService fitnessAppUserService;

    public FitnessAppUserDetailsService(FitnessAppUserService fitnessAppUserService) {
        this.fitnessAppUserService = fitnessAppUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return fitnessAppUserService.findByEmail(username)
                .map(FitnessAppUserDetailsAdapter::new)
                .orElseThrow(() -> new UsernameNotFoundException("Fitness app user with email: " + username + " was not found..."));
    }
}
