package fitnesstracker.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fitnesstracker.model.enums.ApplicationCategory;

public record UserApplicationRegistrationResponseDto(
        String name,
        @JsonProperty(value = "apikey")
        String apiKey,
        @JsonProperty(value = "category")
        ApplicationCategory applicationCategory
) {}
