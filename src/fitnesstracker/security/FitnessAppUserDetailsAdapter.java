package fitnesstracker.security;

import fitnesstracker.model.entity.FitnessAppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class FitnessAppUserDetailsAdapter implements UserDetails {
    private final FitnessAppUser fitnessAppUser;

    public FitnessAppUserDetailsAdapter(FitnessAppUser fitnessAppUser) {
        this.fitnessAppUser = fitnessAppUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return fitnessAppUser.getPassword();
    }

    @Override
    public String getUsername() {
        return fitnessAppUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
