package com.greenwaste.javadatabaseconnector.dtos.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AdminDTO {

    private Long id;
    private Long userId;
    private String citizenCardCode;
}
