package com.greenwaste.javadatabaseconnector.dtos.user.request;


import com.greenwaste.javadatabaseconnector.dtos.base.PostalCodeDTO;
import lombok.Data;

@Data
public class UpdatePostalCodeRequestDTO {
    private PostalCodeDTO postalCode;
}
