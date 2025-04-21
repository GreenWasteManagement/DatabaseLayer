package com.greenwaste.javadatabaseconnector.dtos.bucket.request;


import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDepositRequestDTO {
    private MunicipalityDTO municipality;
    private ContainerDTO container;
    private BigDecimal depositAmount;
}
