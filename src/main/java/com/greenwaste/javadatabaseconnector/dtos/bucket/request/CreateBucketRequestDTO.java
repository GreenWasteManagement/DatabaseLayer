package com.greenwaste.javadatabaseconnector.dtos.bucket.request;

import com.greenwaste.javadatabaseconnector.dtos.base.BucketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBucketRequestDTO {
    private BucketDTO bucket;
}
