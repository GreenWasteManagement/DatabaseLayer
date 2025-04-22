package com.greenwaste.javadatabaseconnector.dtos.olderandnotusednow.bucketwebdto.older;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateBucketAssociationDTO {

    @NotNull(message = "Bucket ID is required")
    private Long bucketId;

    @NotNull(message = "Municipality ID is required")
    private Long municipalityId;

}

