package com.greenwaste.javadatabaseconnector.dtos.basedto;

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

