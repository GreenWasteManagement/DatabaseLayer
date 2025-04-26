package com.greenwaste.javadatabaseconnector.dtos.bucket.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class CreateBucketAssociationResponseDTO {
    private Long id;
    private Bucket bucket;
    private Municipality municipality;
    private Instant timestampOfAssociation;
    private Boolean status;

    @Data
    public static class Bucket {
        private Long id;
        private BigDecimal capacity;
        private Boolean isAssociated;
    }

    @Data
    public static class Municipality {
        private Long id;
        private Long userId;
        private String citizenCardCode;
        private String nif;
    }
}
