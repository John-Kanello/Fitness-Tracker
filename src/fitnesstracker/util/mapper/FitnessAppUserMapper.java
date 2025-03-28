package fitnesstracker.util.mapper;

import fitnesstracker.model.dto.request.FitnessAppUserRegistrationRequestDto;
import fitnesstracker.model.dto.response.FitnessAppUserResponseDto;
import fitnesstracker.model.entity.FitnessAppUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FitnessAppUserMapper implements
        RequestMapper<FitnessAppUser, FitnessAppUserRegistrationRequestDto>,
        ResponseMapper<FitnessAppUser, FitnessAppUserResponseDto> {
    private final PasswordEncoder passwordEncoder;
    private final UserApplicationMapper userApplicationMapper;

    public FitnessAppUserMapper(PasswordEncoder passwordEncoder, UserApplicationMapper userApplicationMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userApplicationMapper = userApplicationMapper;
    }

    @Override
    public FitnessAppUser toEntity(FitnessAppUserRegistrationRequestDto dtoRequest) {
        return new FitnessAppUser(
                dtoRequest.email(),
                passwordEncoder.encode(dtoRequest.password())
        );
    }

    @Override
    public FitnessAppUserResponseDto toResponseDto(FitnessAppUser entity) {
        return new FitnessAppUserResponseDto(
                entity.getId(),
                entity.getEmail(),
                entity.getApplications().stream()
                        .map(userApplicationMapper::toResponseDto)
                        .toList()
        );
    }
}
