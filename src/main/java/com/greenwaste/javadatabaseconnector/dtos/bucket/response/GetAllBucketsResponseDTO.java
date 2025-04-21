package com.greenwaste.javadatabaseconnector.dtos.bucket.response;

import com.greenwaste.javadatabaseconnector.dtos.base.BucketDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllBucketsResponseDTO {
    private List<BucketDTO> buckets;
}