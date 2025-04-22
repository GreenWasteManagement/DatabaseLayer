package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.AddressDTO;
import lombok.Data;

@Data
public class UpdateAddressRequestDTO {
    private AddressDTO address;
}
