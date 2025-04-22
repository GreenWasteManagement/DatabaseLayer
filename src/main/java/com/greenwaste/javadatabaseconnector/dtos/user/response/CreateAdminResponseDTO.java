package com.greenwaste.javadatabaseconnector.dtos.user.response;

import com.greenwaste.javadatabaseconnector.dtos.base.AdminDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminResponseDTO {
    private AdminDTO admin;
}
