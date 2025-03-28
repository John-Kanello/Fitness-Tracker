package fitnesstracker.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserApplicationResponseDto(
        long id,
        String name,
        String description,
        @JsonProperty(value = "apikey")
        String apiKey
) {}
