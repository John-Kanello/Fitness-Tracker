package fitnesstracker.repository;

import fitnesstracker.model.entity.FitnessAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FitnessAppUserRepository extends JpaRepository<FitnessAppUser, Long> {
    Optional<FitnessAppUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
