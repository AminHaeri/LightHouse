package org.amin.fanoos.usermanagement.user.web.middleware;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.web.dto.request.RegisterUserRequestDTO;
import org.amin.fanoos.usermanagement.user.web.util.SecurityUtils;
import org.amin.fanoos.usermanagement.user.web.validation.UserRegisterValidation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Aspect
@Order(1)
public class UserRegistrationMiddleware {

    private final MessageSource messageSource;
    private final UserRegisterValidation userRegisterValidation;

    public UserRegistrationMiddleware(MessageSource messageSource, UserRegisterValidation userRegisterValidation) {
        this.messageSource = messageSource;
        this.userRegisterValidation = userRegisterValidation;
    }

    /**
     * AOP for checking the binding result in controllers. if the BindingResult contains error it must throw a
     * ${{{@link IllegalArgumentException}}}.
     *
     */
    @Before("execution(* org.amin.fanoos..*UserRegistrationController.registerUser(..))")
    public void UserRegistrationValidation(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        RegisterUserRequestDTO registerUserRequestDTO = null;
        for (Object arg : args) {
            if (arg instanceof RegisterUserRequestDTO) {
                registerUserRequestDTO = (RegisterUserRequestDTO) arg;
            }
        }

        if (registerUserRequestDTO == null)
            throw new IllegalArgumentException("The structure of input does not match");

        ERole currentUserRole = SecurityUtils.getCurrentUserRole();
        if (!userRegisterValidation
                .validateRoleAuthorityToRegister(currentUserRole, registerUserRequestDTO.getRoles().get(0))) {
            throw new AuthorizationServiceException(messageSource.getMessage(
                    "error.unauthorized.roles",
                    new Object[]{
                            currentUserRole.name(),
                            registerUserRequestDTO.getUserName(),
                            registerUserRequestDTO.getRoles().get(0).name()
                    },
                    Locale.getDefault()));
        }
    }
}
