package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.AddressDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.PostalCodeDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateMunicipalityRequestDTO {
    private UserDTO user;
    private MunicipalityDTO municipality;
    private AddressDTO address;
    private PostalCodeDTO postalCode;
}
