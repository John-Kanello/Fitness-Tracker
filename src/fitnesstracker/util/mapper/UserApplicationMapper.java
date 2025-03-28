package fitnesstracker.util.mapper;

import fitnesstracker.model.dto.response.UserApplicationResponseDto;
import fitnesstracker.model.entity.UserApplication;
import org.springframework.stereotype.Component;

@Component
public class UserApplicationMapper implements ResponseMapper<UserApplication, UserApplicationResponseDto> {
    @Override
    public UserApplicationResponseDto toResponseDto(UserApplication entity) {
        return new UserApplicationResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getApiKey()
        );
    }
}
