package fitnesstracker.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserApplicationResponseDto(
        String name,
        @JsonProperty(value = "apikey")
        String apiKey
) {}
