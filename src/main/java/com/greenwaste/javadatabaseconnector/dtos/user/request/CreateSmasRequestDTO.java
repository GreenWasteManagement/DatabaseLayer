package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.AddressDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.PostalCodeDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.UserDTO;
import lombok.Data;

@Data
public class CreateSmasRequestDTO {
    private UserDTO user;
    private SmasDTO smas;
    private AddressDTO address;
    private PostalCodeDTO postalCode;
}

