package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DeleteUserRequestDTO {
    private UserDTO user;
}
