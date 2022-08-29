package org.amin.fanoos.usermanagement.user.web.middleware;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@Aspect
@Order(1)
public class RequestBindingResultMiddleware {

    private final MessageSource messageSource;

    public RequestBindingResultMiddleware(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * AOP for checking the binding result in controllers. if the BindingResult contains error it must throw a
     * ${{{@link IllegalArgumentException}}}.
     * @param joinPoint
     */
    @Before("execution(* org.amin.fanoos..*Controller.*(..,org.springframework.validation.BindingResult,..))")
    public void BindingResultControllerValidation(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {
            if (arg instanceof BindingResult bindingResult) {
                String error = extractBindingValidationError(bindingResult);
                if (error != null && !error.isEmpty())
                    throw new IllegalArgumentException(error);
            }
        }
    }

    private String extractBindingValidationError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder error = new StringBuilder();
            for (Object object : bindingResult.getAllErrors()) {
                if(object instanceof FieldError fieldError) {
                    String message = messageSource.getMessage(fieldError, null);
                    if (!error.isEmpty())
                        error.append(", ");
                    error.append(message);
                }
            }
            return error.toString();
        }
        return null;
    }
}
