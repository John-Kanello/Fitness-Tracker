package fitnesstracker.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FitnessTrackerRequestDto(
        @NotBlank
        String username,
        @NotBlank
        String activity,
        @NotNull
        Integer duration,
        @NotNull
        Integer calories
) {}
