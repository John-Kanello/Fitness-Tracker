package fitnesstracker.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserApplicationRegistrationResponseDto(
        String name,
        @JsonProperty(value = "apikey")
        String apiKey
) {}
