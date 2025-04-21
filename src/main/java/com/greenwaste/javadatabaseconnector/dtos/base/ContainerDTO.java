package com.greenwaste.javadatabaseconnector.dtos.base;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class ContainerDTO {

    private Long id;
    private BigDecimal capacity;
    private String localization;
    private BigDecimal currentVolumeLevel;

}

