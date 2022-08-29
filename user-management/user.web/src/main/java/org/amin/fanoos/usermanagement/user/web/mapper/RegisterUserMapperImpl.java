package org.amin.fanoos.usermanagement.user.web.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.RegisterUserCommand;
import org.amin.fanoos.usermanagement.user.web.dto.request.RegisterUserRequestDTO;
import org.amin.fanoos.usermanagement.user.web.dto.response.RegisterUserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RegisterUserMapperImpl implements RegisterUserMapper {

    @Override
    public RegisterUserCommand toCmd(RegisterUserRequestDTO registerUserRequestDTO) {
        return new RegisterUserCommand(
                registerUserRequestDTO.getUserName(),
                registerUserRequestDTO.getPassword(),
                registerUserRequestDTO.getEmail(),
                registerUserRequestDTO.getFirstName(),
                registerUserRequestDTO.getLastName(),
                registerUserRequestDTO.getRoles());
    }

    @Override
    public RegisterUserResponseDTO toResponse(User user) {
        return new RegisterUserResponseDTO(new RegisterUserResponseDTO.UserData(
                user.getAccount().getUserName(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getAccount().getRoles().stream().map(Role::getName).collect(Collectors.toList())
        ));
    }
}
