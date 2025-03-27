package fitnesstracker.controller;

import fitnesstracker.model.dto.request.FitnessTrackerRequestDto;
import fitnesstracker.model.dto.response.FitnessTrackerResponseDto;
import fitnesstracker.model.entity.FitnessTracker;
import fitnesstracker.model.entity.UserApplication;
import fitnesstracker.service.FitnessTrackerService;
import fitnesstracker.service.UserApplicationService;
import fitnesstracker.util.mapper.FitnessTrackerMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracker")
public class FitnessTrackerController {
    private final FitnessTrackerService fitnessTrackerService;
    private final UserApplicationService userApplicationService;
    private final FitnessTrackerMapper fitnessTrackerMapper;

    public FitnessTrackerController(FitnessTrackerService fitnessTrackerService,
                                    UserApplicationService userApplicationService,
                                    FitnessTrackerMapper fitnessTrackerMapper) {
        this.fitnessTrackerService = fitnessTrackerService;
        this.userApplicationService = userApplicationService;
        this.fitnessTrackerMapper = fitnessTrackerMapper;
    }

    @PostMapping
    public ResponseEntity<?> postTracker(@Valid @RequestBody FitnessTrackerRequestDto fitnessTrackerRequestDto,
                                         Authentication authentication) {
        String apiKey = authentication.getCredentials().toString();
        UserApplication userApplication = userApplicationService.findByApiKey(apiKey)
                .orElseThrow();
        fitnessTrackerRequestDto.setApplication(userApplication.getName());
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
