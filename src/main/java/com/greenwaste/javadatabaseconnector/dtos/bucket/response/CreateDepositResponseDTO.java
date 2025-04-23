package com.greenwaste.javadatabaseconnector.dtos.bucket.response;


import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Data
public class CreateDepositResponseDTO {
    private Instant depositTimestamp;
}
