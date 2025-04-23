package com.greenwaste.javadatabaseconnector.dtos.bucket.request;

import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Data
public class GetFirstBucketMunicipalityByUserAndStatusTrueRequestDTO {
    private Municipality municipality;

    @Getter
    @Setter
    @Data
    public static class Municipality {
        private Long id;
        private Long userId;
        private String citizenCardCode;
        private String nif;

    }
}
