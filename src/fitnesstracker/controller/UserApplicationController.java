package fitnesstracker.controller;

import fitnesstracker.model.dto.request.UserApplicationRequestDto;
import fitnesstracker.model.dto.response.UserApplicationResponseDto;
import fitnesstracker.model.entity.FitnessAppUser;
import fitnesstracker.model.entity.UserApplication;
import fitnesstracker.service.FitnessAppUserService;
import fitnesstracker.service.UserApplicationService;
import fitnesstracker.util.mapper.UserApplicationMapper;
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
    private final UserApplicationMapper userApplicationMapper;

    public UserApplicationController(UserApplicationService userApplicationService, FitnessAppUserService fitnessAppUserService, UserApplicationMapper userApplicationMapper) {
        this.userApplicationService = userApplicationService;
        this.fitnessAppUserService = fitnessAppUserService;
        this.userApplicationMapper = userApplicationMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<UserApplicationResponseDto> registerApplication(@Valid @RequestBody UserApplicationRequestDto userApplicationRequestDto,
                                                                          Authentication authentication) {
        String currentUserEmail = authentication.getName();
        if(!fitnessAppUserService.existsByEmail(currentUserEmail)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        FitnessAppUser fitnessAppUser = fitnessAppUserService.findByEmail(currentUserEmail)
                .orElseThrow();
        UserApplication userApplication = userApplicationMapper.toEntity(userApplicationRequestDto);
        userApplication.setFitnessAppUser(fitnessAppUser);
        fitnessAppUser.getApplications().add(userApplication);
        UserApplication savedUserApplication = userApplicationService.save(userApplication);
        return new ResponseEntity<>(
                userApplicationMapper.toDtoResponse(savedUserApplication),
                HttpStatus.CREATED
        );
    }
}
