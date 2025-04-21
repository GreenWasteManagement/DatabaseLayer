package com.greenwaste.javadatabaseconnector.dtos.base;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SmasDTO {

    private Long id;
    private String position;
    private String employeeCode;
    private String citizenCardCode;

}

