package fitnesstracker.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import fitnesstracker.model.enums.ApplicationCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserApplicationRegistrationRequestDto(
        @NotBlank
        String name,
        @NotNull
        String description,
        @NotNull
        @JsonProperty(value = "category")
        ApplicationCategory applicationCategory
) {}

