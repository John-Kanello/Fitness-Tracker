package fitnesstracker.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FitnessAppUserRequestDto(
        @NotBlank
        @Email(regexp = ".+[@].+[\\.].+")
        String email,
        @NotBlank
        String password
) {}