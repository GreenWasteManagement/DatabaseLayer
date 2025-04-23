package com.greenwaste.javadatabaseconnector.dtos.user.request;

import com.greenwaste.javadatabaseconnector.dtos.base.PostalCodeDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DeletePostalCodeRequestDTO {
    private PostalCodeDTO postalCode;
}
