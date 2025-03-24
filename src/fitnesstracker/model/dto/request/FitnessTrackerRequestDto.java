package fitnesstracker.model.dto.request;

public record FitnessTrackerRequestDto(
        String username,
        String activity,
        int duration,
        int calories
) {}
