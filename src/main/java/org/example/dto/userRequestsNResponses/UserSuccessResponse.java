package org.example.dto.userRequestsNResponses;

import lombok.Getter;

@Getter
public class UserSuccessResponse extends UserResponse{

    private final String name;
    private final String pass;
    private final String rights;

    public UserSuccessResponse(String name, String pass, String rights) {
        this.name = name;
        this.pass = pass;
        this.rights = rights;
    }
}
