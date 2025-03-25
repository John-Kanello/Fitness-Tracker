package fitnesstracker.repository;

import fitnesstracker.model.entity.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplicationRepository extends JpaRepository<UserApplication, Long> {
}
