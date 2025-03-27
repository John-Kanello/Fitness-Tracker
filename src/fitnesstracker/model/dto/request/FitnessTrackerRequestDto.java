package fitnesstracker.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class FitnessTrackerRequestDto {
        @NotBlank
        private String username;
        @NotBlank
        private String activity;
        @NotNull
        private Integer duration;
        @NotNull
        private Integer calories;
        private String application;
}