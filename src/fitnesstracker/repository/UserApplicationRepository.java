package fitnesstracker.repository;

import fitnesstracker.model.entity.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserApplicationRepository extends JpaRepository<UserApplication, Long> {
    Optional<UserApplication> findByApiKey(String apiKey);
    boolean existsByApiKey(String apiKey);
}
