package com.greenwaste.javadatabaseconnector.dtos.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PostalCodeDTO {

    private Long id;
    private String postalCode;
    private String county;
    private String district;

}

