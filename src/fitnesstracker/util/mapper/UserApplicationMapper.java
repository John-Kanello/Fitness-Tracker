package fitnesstracker.util.mapper;

import fitnesstracker.model.dto.request.UserApplicationRequestDto;
import fitnesstracker.model.dto.response.UserApplicationResponseDto;
import fitnesstracker.model.entity.UserApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserApplicationMapper implements
        RequestMapper<UserApplication, UserApplicationRequestDto>,
        ResponseMapper<UserApplication, UserApplicationResponseDto> {
    @Override
    public UserApplication toEntity(UserApplicationRequestDto dtoRequest) {
        return new UserApplication(
                dtoRequest.name(),
                dtoRequest.description(),
                UUID.randomUUID().toString(),
                LocalDateTime.now()
        );
    }

    @Override
    public UserApplicationResponseDto toResponseDto(UserApplication entity) {
        return new UserApplicationResponseDto(
                entity.getName(),
                entity.getApiKey()
        );
    }
}
