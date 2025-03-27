package fitnesstracker.service;

import fitnesstracker.model.entity.UserApplication;
import fitnesstracker.repository.UserApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationService {
    private final UserApplicationRepository userApplicationRepository;

    public UserApplicationService(UserApplicationRepository userApplicationRepository) {
        this.userApplicationRepository = userApplicationRepository;
    }

    public Optional<UserApplication> findByApiKey(String apiKey) {
        return userApplicationRepository.findByApiKey(apiKey);
    }

    public boolean existsByApiKey(String apiKey) {
        return userApplicationRepository.existsByApiKey(apiKey);
    }

    public UserApplication save(UserApplication userApplication) {
        return userApplicationRepository.save(userApplication);
    }
}
