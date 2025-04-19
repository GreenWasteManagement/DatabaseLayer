package com.greenwaste.javadatabaseconnector.dtos.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostalCodeDTO {

    private Long id;
    private String postalCode;
    private String county;
    private String district;

}

