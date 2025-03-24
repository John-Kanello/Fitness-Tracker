package fitnesstracker.service;

import fitnesstracker.model.entity.FitnessTracker;
import fitnesstracker.repository.FitnessTrackerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessTrackerService {
    private final FitnessTrackerRepository fitnessTrackerRepository;

    public FitnessTrackerService(FitnessTrackerRepository fitnessTrackerRepository) {
        this.fitnessTrackerRepository = fitnessTrackerRepository;
    }

    public List<FitnessTracker> findAllOrderByUploadDateDescending() {
        return fitnessTrackerRepository.findAllOrderByUploadDateDescending();
    }

    public FitnessTracker save(FitnessTracker fitnessTracker) {
        return fitnessTrackerRepository.save(fitnessTracker);
    }
}
