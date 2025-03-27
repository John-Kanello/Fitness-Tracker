package fitnesstracker.model.dto.response;

public record FitnessTrackerResponseDto(
        long id,
        String username,
        String activity,
        int duration,
        int calories,
        String application
) {}
