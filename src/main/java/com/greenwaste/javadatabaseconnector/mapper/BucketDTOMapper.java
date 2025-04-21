package com.greenwaste.javadatabaseconnector.mapper;

import com.greenwaste.javadatabaseconnector.dtos.base.BucketDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.BucketMunicipalityDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.request.CreateBucketRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.request.UpdateBucketRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.CreateBucketAssociationResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.CreateBucketResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.GetAllBucketsResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.GetBucketByIdResponseDTO;
import com.greenwaste.javadatabaseconnector.model.Bucket;
import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.Municipality;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BucketDTOMapper {

    BucketDTOMapper INSTANCE = Mappers.getMapper(BucketDTOMapper.class);

    BucketDTO toBucketDTO(Bucket bucket);

    @Mapping(source = "bucket", target = "bucket")
    GetBucketByIdResponseDTO toGetBucketByIdResponseDTO(Bucket bucket);


    List<BucketDTO> toBucketDTOList(List<Bucket> buckets);

    @Mapping(source = "buckets", target = "buckets")
    GetAllBucketsResponseDTO toGetAllBucketsResponseDTO(List<Bucket> buckets);


    // Create
    @Mapping(source = "bucket", target = "bucket")
    CreateBucketResponseDTO toCreateBucketResponseDTO(Bucket bucket);

    @Mapping(target = "id", ignore = true)
    Bucket toBucket(CreateBucketRequestDTO dto);

    // Update
    Bucket toBucket(UpdateBucketRequestDTO dto);

    // Delete (sem conversão complexa necessária)


    Municipality toMunicipality(MunicipalityDTO dto);

    Container toContainer(ContainerDTO dto);

    BucketMunicipalityDTO toDTO(BucketMunicipality bucketMunicipality);

    List<BucketMunicipalityDTO> toDTOList(List<BucketMunicipality> list);

    Municipality toEntity(MunicipalityDTO dto);

    @Mapping(source = "bucket", target = "bucket")
    @Mapping(source = "user", target = "municipality")
    CreateBucketAssociationResponseDTO toCreateBucketAssociationResponseDTO(BucketMunicipality association);


    MunicipalityDTO toMunicipalityDTO(Municipality municipality);

}
