package com.greenwaste.javadatabaseconnector.dtos.container;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContainerRequestDTO {
    private BigDecimal capacity;
    private BigDecimal currentVolumeLevel;
}
