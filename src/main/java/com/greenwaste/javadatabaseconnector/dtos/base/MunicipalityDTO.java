package com.greenwaste.javadatabaseconnector.dtos.base;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class MunicipalityDTO {

    private Long id;
    private String citizenCardCode;
    private String nif;


}

