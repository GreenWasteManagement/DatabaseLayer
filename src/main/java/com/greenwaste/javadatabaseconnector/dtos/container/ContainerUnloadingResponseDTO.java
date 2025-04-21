package com.greenwaste.javadatabaseconnector.dtos.container;

import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ContainerUnloadingResponseDTO {
    private BigDecimal unloadedQuantity;
    private Instant unloadingTimestamp;
    private SmasDTO user;
    private ContainerDTO container;
}
