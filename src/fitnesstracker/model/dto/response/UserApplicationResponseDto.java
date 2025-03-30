package fitnesstracker.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fitnesstracker.model.enums.ApplicationCategory;

public record UserApplicationResponseDto(
        long id,
        String name,
        String description,
        @JsonProperty(value = "apikey")
        String apiKey,
        @JsonProperty(value = "category")
        ApplicationCategory applicationCategory
) {}
