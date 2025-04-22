package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.UserDTO;
import lombok.Data;

@Data
public class DeleteUserRequestDTO {
    private UserDTO user;
}
