package com.greenwaste.javadatabaseconnector.dtos.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private Long id;
    private Long userId;
    private String floorDetails;
    private Integer floorNumber;
    private Integer doorNumber;
    private String street;
    private Long postalCodeId;
}
