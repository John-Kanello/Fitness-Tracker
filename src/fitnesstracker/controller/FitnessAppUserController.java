package fitnesstracker.controller;

import fitnesstracker.model.dto.request.FitnessAppUserRequestDto;
import fitnesstracker.model.dto.response.FitnessAppUserResponseDto;
import fitnesstracker.model.entity.FitnessAppUser;
import fitnesstracker.util.mapper.FitnessAppUserMapper;
import fitnesstracker.service.FitnessAppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.MessageFormat;

@RestController
@RequestMapping("/api/developers")
public class FitnessAppUserController {
    private final FitnessAppUserService fitnessAppUserService;
    private final FitnessAppUserMapper fitnessAppUserMapper;

    public FitnessAppUserController(FitnessAppUserService fitnessAppUserService,
                                    FitnessAppUserMapper fitnessAppUserMapper) {
        this.fitnessAppUserService = fitnessAppUserService;
        this.fitnessAppUserMapper = fitnessAppUserMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody FitnessAppUserRequestDto fitnessAppUserRequestDto,
                                          HttpServletRequest servletRequest) {
        if(fitnessAppUserService.existsByEmail(fitnessAppUserRequestDto.email())) {
            return ResponseEntity.badRequest()
                    .build();
        }
        FitnessAppUser fitnessAppUser = fitnessAppUserMapper.toEntity(fitnessAppUserRequestDto);
        FitnessAppUser savedFitnessAppUser = fitnessAppUserService.save(fitnessAppUser);
        return ResponseEntity.created(URI.create(MessageFormat.format("{0}/{1}",
                        servletRequest.getRequestURI().substring(0,servletRequest.getRequestURI().lastIndexOf("/")),
                        savedFitnessAppUser.getId())))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FitnessAppUserResponseDto> findUserById(@PathVariable("id") long id,
                                                                  Authentication authentication) {
        if(!fitnessAppUserService.existsById(id)) {
            return ResponseEntity.notFound()
                    .build();
        }
        String currentUserEmail = authentication.getName();
        if(!fitnessAppUserService.existsByEmail(currentUserEmail)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        FitnessAppUser currentUser = fitnessAppUserService.findByEmail(currentUserEmail)
                .orElseThrow();
        if(currentUser.getId() != id) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok()
                .body(fitnessAppUserMapper.toDtoResponse(currentUser));
    }
}
