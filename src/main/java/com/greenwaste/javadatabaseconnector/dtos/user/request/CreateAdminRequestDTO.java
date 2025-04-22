package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.AddressDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.AdminDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.PostalCodeDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.UserDTO;
import lombok.Data;

@Data
public class CreateAdminRequestDTO {
    private UserDTO user;
    private AdminDTO admin;
    private AddressDTO address;
    private PostalCodeDTO postalCode;
}

