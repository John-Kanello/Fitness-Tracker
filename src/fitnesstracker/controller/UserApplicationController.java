package fitnesstracker.controller;

import fitnesstracker.model.dto.request.UserApplicationRegistrationRequestDto;
import fitnesstracker.model.dto.response.UserApplicationRegistrationResponseDto;
import fitnesstracker.model.entity.FitnessAppUser;
import fitnesstracker.model.entity.UserApplication;
import fitnesstracker.service.FitnessAppUserService;
import fitnesstracker.service.UserApplicationService;
import fitnesstracker.util.mapper.UserApplicationRegistrationMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
public class UserApplicationController {
    private final UserApplicationService userApplicationService;
    private final FitnessAppUserService fitnessAppUserService;
    private final UserApplicationRegistrationMapper userApplicationRegistrationMapper;

    public UserApplicationController(UserApplicationService userApplicationService,
                                     FitnessAppUserService fitnessAppUserService,
                                     UserApplicationRegistrationMapper userApplicationRegistrationMapper) {
        this.userApplicationService = userApplicationService;
        this.fitnessAppUserService = fitnessAppUserService;
        this.userApplicationRegistrationMapper = userApplicationRegistrationMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserApplicationRegistrationResponseDto> registerApplication(@Valid @RequestBody UserApplicationRegistrationRequestDto userApplicationRequestDto,
                                                                                      Authentication authentication) {
        String currentUserEmail = authentication.getName();
        if(!fitnessAppUserService.existsByEmail(currentUserEmail)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        FitnessAppUser fitnessAppUser = fitnessAppUserService.findByEmail(currentUserEmail)
                .orElseThrow();
        UserApplication userApplication = userApplicationRegistrationMapper.toEntity(userApplicationRequestDto);
        userApplication.setFitnessAppUser(fitnessAppUser);
        fitnessAppUser.getApplications().add(userApplication);
        UserApplication savedUserApplication = userApplicationService.save(userApplication);
        return new ResponseEntity<>(
                userApplicationRegistrationMapper.toResponseDto(savedUserApplication),
                HttpStatus.CREATED
        );
    }
}
