package fitnesstracker.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserApplicationRequestDto(
        @NotBlank
        String name,
        @NotNull
        String description
) { }

