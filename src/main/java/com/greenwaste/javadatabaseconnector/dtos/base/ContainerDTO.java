package com.greenwaste.javadatabaseconnector.dtos.base;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ContainerDTO {

    private Long id;

    private BigDecimal capacity;

    private String localization;

    private BigDecimal currentVolumeLevel;
}
