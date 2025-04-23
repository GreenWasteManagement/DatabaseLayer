package com.greenwaste.javadatabaseconnector.dtos.user.response;

import com.greenwaste.javadatabaseconnector.dtos.base.AdminDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.request.CreateAdminRequestDTO;
import lombok.*;

@Getter
@Setter
@Data
public class CreateAdminResponseDTO {
    private CreateAdminRequestDTO.Admin admin;

    @Getter
    @Setter
    @Data
    public static class Admin {
        private String citizenCardCode;
    }
}
