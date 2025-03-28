package fitnesstracker.model.dto.response;

import java.util.List;

public record FitnessAppUserResponseDto(
        long id,
        String email,
        List<UserApplicationResponseDto> applications
) {}
