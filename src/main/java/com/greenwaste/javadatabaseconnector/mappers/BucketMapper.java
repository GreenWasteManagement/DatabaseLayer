package com.greenwaste.javadatabaseconnector.mappers;

import com.greenwaste.javadatabaseconnector.dtos.bucketwebdto.BucketDTO;
import com.greenwaste.javadatabaseconnector.model.Bucket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BucketMapper {
    BucketDTO toDto(Bucket bucket);

    Bucket toEntity(BucketDTO dto);

    List<BucketDTO> toDtoList(List<Bucket> buckets);
}
