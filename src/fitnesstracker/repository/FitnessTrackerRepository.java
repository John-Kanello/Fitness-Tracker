package fitnesstracker.repository;

import fitnesstracker.model.entity.FitnessTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FitnessTrackerRepository extends JpaRepository<FitnessTracker, Long> {
    @Query(value = """
            SELECT * FROM trackers t
            ORDER BY t.upload_date DESC
            """, nativeQuery = true)
    List<FitnessTracker> findAllOrderByUploadDateDescending();
}
