package fitnesstracker.util.mapper;

import fitnesstracker.model.dto.request.UserApplicationRegistrationRequestDto;
import fitnesstracker.model.dto.response.UserApplicationRegistrationResponseDto;
import fitnesstracker.model.entity.UserApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserApplicationRegistrationMapper implements
        RequestMapper<UserApplication, UserApplicationRegistrationRequestDto>,
        ResponseMapper<UserApplication, UserApplicationRegistrationResponseDto> {
    @Override
    public UserApplication toEntity(UserApplicationRegistrationRequestDto dtoRequest) {
        return new UserApplication(
                dtoRequest.name(),
                dtoRequest.description(),
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                dtoRequest.applicationCategory()
        );
    }

    @Override
    public UserApplicationRegistrationResponseDto toResponseDto(UserApplication entity) {
        return new UserApplicationRegistrationResponseDto(
                entity.getName(),
                entity.getApiKey(),
                entity.getApplicationCategory()
        );
    }
}
