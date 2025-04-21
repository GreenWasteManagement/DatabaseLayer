package com.greenwaste.javadatabaseconnector.dtos.container;

import com.greenwaste.javadatabaseconnector.dtos.base.BucketDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class UpdateContainerRequestDTO {
    private ContainerDTO container;
}
