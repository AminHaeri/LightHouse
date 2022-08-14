package org.amin.fanoos.usermanagement.user.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequestDTO {

    @NotNull(message = "'userName' property must be provided")
    @Pattern(regexp = "^[a-zA-Z].*", message = "'name' must start with a character")
    @JsonProperty("userName")
    private String userName;

    @NotNull(message = "'password' property must be provided")
    @JsonProperty("password")
    private String password;

    @NotNull(message = "'email' property must be provided")
    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("roles")
    private List<ERole> roles;
}
