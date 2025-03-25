package fitnesstracker.util.mapper;

import fitnesstracker.model.dto.request.FitnessAppUserRequestDto;
import fitnesstracker.model.dto.response.FitnessAppUserResponseDto;
import fitnesstracker.model.entity.FitnessAppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FitnessAppUserMapper implements Mapper<FitnessAppUser, FitnessAppUserRequestDto, FitnessAppUserResponseDto> {
    private final PasswordEncoder passwordEncoder;

    public FitnessAppUserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public FitnessAppUser toEntity(FitnessAppUserRequestDto dtoRequest) {
        return new FitnessAppUser(
                dtoRequest.email(),
                passwordEncoder.encode(dtoRequest.password())
        );
    }

    @Override
    public FitnessAppUserResponseDto toDtoResponse(FitnessAppUser entity) {
        return new FitnessAppUserResponseDto(
                entity.getId(),
                entity.getEmail()
        );
    }
}
