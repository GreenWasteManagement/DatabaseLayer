package com.greenwaste.javadatabaseconnector.dtos.olderandnotusednow.bucketwebdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BucketDTO {
    private Long id;
    private BigDecimal capacity;
    private Boolean isAssociated;
}
