package fitnesstracker.controller;

import fitnesstracker.model.dto.request.FitnessTrackerRequestDto;
import fitnesstracker.model.dto.response.FitnessTrackerResponseDto;
import fitnesstracker.model.entity.FitnessTracker;
import fitnesstracker.service.FitnessTrackerService;
import fitnesstracker.util.mapper.FitnessTrackerMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracker")
public class FitnessTrackerController {
    private final FitnessTrackerService fitnessTrackerService;
    private final FitnessTrackerMapper fitnessTrackerMapper;

    public FitnessTrackerController(FitnessTrackerService fitnessTrackerService, FitnessTrackerMapper fitnessTrackerMapper) {
        this.fitnessTrackerService = fitnessTrackerService;
        this.fitnessTrackerMapper = fitnessTrackerMapper;
    }

    @PostMapping
    public ResponseEntity<?> postTracker(@RequestBody FitnessTrackerRequestDto fitnessTrackerRequestDto) {
        FitnessTracker fitnessTracker = fitnessTrackerMapper.toEntity(fitnessTrackerRequestDto);
        fitnessTrackerService.save(fitnessTracker);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FitnessTrackerResponseDto>> findAllTrackers() {
        return ResponseEntity.ok()
                .body(
                        fitnessTrackerService.findAllOrderByUploadDateDescending().stream()
                                .map(fitnessTrackerMapper::toDtoResponse)
                                .toList()
                );
    }
}
