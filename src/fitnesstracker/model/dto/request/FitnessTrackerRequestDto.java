package fitnesstracker.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FitnessTrackerRequestDto(
        @NotBlank
        String username,
        @NotBlank
        String activity,
        @NotBlank
        int duration,
        @NotBlank
        int calories
) {}
