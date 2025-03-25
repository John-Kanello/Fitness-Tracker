package fitnesstracker.util.mapper;

import fitnesstracker.model.dto.request.UserApplicationRequestDto;
import fitnesstracker.model.dto.response.UserApplicationResponseDto;
import fitnesstracker.model.entity.UserApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserApplicationMapper implements Mapper<UserApplication, UserApplicationRequestDto, UserApplicationResponseDto> {
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
    public UserApplicationResponseDto toDtoResponse(UserApplication entity) {
        return new UserApplicationResponseDto(
                entity.getName(),
                entity.getApiKey()
        );
    }
}
