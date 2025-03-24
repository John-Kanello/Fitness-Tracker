package fitnesstracker.util.mapper;

import fitnesstracker.model.dto.request.FitnessTrackerRequestDto;
import fitnesstracker.model.dto.response.FitnessTrackerResponseDto;
import fitnesstracker.model.entity.FitnessTracker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FitnessTrackerMapper implements Mapper<FitnessTracker, FitnessTrackerRequestDto, FitnessTrackerResponseDto> {
    @Override
    public FitnessTracker toEntity(FitnessTrackerRequestDto dtoRequest) {
        return new FitnessTracker(
                dtoRequest.username(),
                dtoRequest.activity(),
                dtoRequest.duration(),
                dtoRequest.calories(),
                LocalDateTime.now()
        );
    }

    @Override
    public FitnessTrackerResponseDto toDtoResponse(FitnessTracker entity) {
        return new FitnessTrackerResponseDto(
                entity.getId(),
                entity.getUsername(),
                entity.getActivity(),
                entity.getDuration(),
                entity.getCalories()
        );
    }
}
