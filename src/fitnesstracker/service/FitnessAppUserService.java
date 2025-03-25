package fitnesstracker.service;

import fitnesstracker.model.entity.FitnessAppUser;
import fitnesstracker.repository.FitnessAppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FitnessAppUserService {
    private final FitnessAppUserRepository fitnessAppUserRepository;

    public FitnessAppUserService(FitnessAppUserRepository fitnessAppUserRepository) {
        this.fitnessAppUserRepository = fitnessAppUserRepository;
    }

    public Optional<FitnessAppUser> findById(long id) {
        return fitnessAppUserRepository.findById(id);
    }

    public Optional<FitnessAppUser> findByEmail(String email) {
        return fitnessAppUserRepository.findByEmail(email);
    }

    public boolean existsById(long id) {
        return fitnessAppUserRepository.existsById(id);
    }

    public boolean existsByEmail(String email) {
        return fitnessAppUserRepository.existsByEmail(email);
    }

    public FitnessAppUser save(FitnessAppUser fitnessAppUser) {
        return fitnessAppUserRepository.save(fitnessAppUser);
    }
}
