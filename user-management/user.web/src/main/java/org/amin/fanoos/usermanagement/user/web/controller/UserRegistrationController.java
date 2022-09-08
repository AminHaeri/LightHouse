package org.amin.fanoos.usermanagement.user.web.controller;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.port.in.command.RegisterUserCommand;
import org.amin.fanoos.usermanagement.user.application.port.in.usecase.RegisterUserUseCase;
import org.amin.fanoos.usermanagement.user.web.dto.request.RegisterUserRequestDTO;
import org.amin.fanoos.usermanagement.user.web.dto.response.RegisterUserResponseDTO;
import org.amin.fanoos.usermanagement.user.web.mapper.RegisterUserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserRegistrationController {

    private final RegisterUserMapper registerUserMapper;
    private final RegisterUserUseCase registerUserUseCase;

    public UserRegistrationController(
            RegisterUserMapper registerUserMapper,
            RegisterUserUseCase registerUserUseCase) {
        this.registerUserMapper = registerUserMapper;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping
    @PreAuthorize(
            "hasAuthority(T(org.amin.fanoos.usermanagement.user.application.domain.ERole).ROLE_SUPERADMIN) or " +
            "hasAuthority(T(org.amin.fanoos.usermanagement.user.application.domain.ERole).ROLE_ADMIN)"
    )
    public ResponseEntity<RegisterUserResponseDTO> registerUser(
            @Valid @RequestBody RegisterUserRequestDTO registerUserRequestDTO,
            BindingResult bindingResult) {
        RegisterUserCommand cmd = registerUserMapper.toCmd(registerUserRequestDTO);
        RegisterUserResponseDTO registerUserResponseDTO =
                registerUserMapper.toResponse(registerUserUseCase.registerUser(cmd));

        return new ResponseEntity<>(registerUserResponseDTO, HttpStatus.CREATED);
    }
}
