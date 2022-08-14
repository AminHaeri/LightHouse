package org.amin.fanoos.usermanagement.user.web.mapper;

import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.amin.fanoos.usermanagement.user.application.port.in.command.RegisterUserCommand;
import org.amin.fanoos.usermanagement.user.web.dto.request.RegisterUserRequestDTO;
import org.amin.fanoos.usermanagement.user.web.dto.response.RegisterUserResponseDTO;

public interface RegisterUserMapper {
    RegisterUserCommand toCmd(RegisterUserRequestDTO registerUserRequestDTO);
    RegisterUserResponseDTO toResponse(User user);
}
