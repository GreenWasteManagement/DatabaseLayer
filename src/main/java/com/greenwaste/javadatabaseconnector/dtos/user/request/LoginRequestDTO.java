package com.greenwaste.javadatabaseconnector.dtos.user.request;

import lombok.*;

@Data
@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;
}
