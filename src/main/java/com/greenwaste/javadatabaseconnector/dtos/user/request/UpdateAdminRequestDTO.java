package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.AdminDTO;
import lombok.Data;

@Data
public class UpdateAdminRequestDTO {
    private AdminDTO admin;
}
