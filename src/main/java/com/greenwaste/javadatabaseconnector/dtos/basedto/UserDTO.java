package com.greenwaste.javadatabaseconnector.dtos.basedto;

import com.greenwaste.javadatabaseconnector.model.User.user_role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private user_role role;

}

