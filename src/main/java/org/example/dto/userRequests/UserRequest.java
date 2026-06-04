package org.example.dto.userRequests;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String login;
    private String pass;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private String rights = "USER";
}
