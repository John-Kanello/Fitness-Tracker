package fitnesstracker.service;

import fitnesstracker.model.entity.UserApplication;
import fitnesstracker.repository.UserApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {
    private final UserApplicationRepository userApplicationRepository;

    public UserApplicationService(UserApplicationRepository userApplicationRepository) {
        this.userApplicationRepository = userApplicationRepository;
    }

    public UserApplication save(UserApplication userApplication) {
        return userApplicationRepository.save(userApplication);
    }
}
