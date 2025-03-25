package fitnesstracker.model.dto.response;

import fitnesstracker.model.entity.UserApplication;

import java.util.List;

public record FitnessAppUserResponseDto(
        long id,
        String email,
        List<UserApplication> applications
) {}
