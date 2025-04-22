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

    // Instance of the mapper for direct access to mapping methods
    BucketDTOMapper INSTANCE = Mappers.getMapper(BucketDTOMapper.class);

    // Converts a single Bucket entity to a BucketDTO.
    BucketDTO toBucketDTO(Bucket bucket);

    // Converts a single Bucket entity to GetBucketByIdResponseDTO.
    GetBucketByIdResponseDTO toGetBucketByIdResponseDTO(Bucket bucket);

    // Converts a list of Bucket entities to a list of BucketDTOs.
    List<BucketDTO> toBucketDTOList(List<Bucket> buckets);

    // Maps the list of Bucket entities to GetAllBucketsResponseDTO by mapping the list to the 'buckets' field.

    /**
     * Wraps a list of BucketDTOs inside a GetAllBucketsResponseDTO.
     * This avoids the "iterable to non-iterable" error in MapStruct,
     * since MapStruct can't directly map List<Bucket> into a wrapper object.
     */
    default GetAllBucketsResponseDTO toGetAllBucketsResponseDTO(List<Bucket> buckets) {
        List<BucketDTO> dtos = toBucketDTOList(buckets);
        GetAllBucketsResponseDTO response = new GetAllBucketsResponseDTO();
        response.setBuckets(dtos);
        return response;
    }

    // Creates a CreateBucketResponseDTO from a Bucket entity.
    CreateBucketResponseDTO toCreateBucketResponseDTO(Bucket bucket);

    // Converts CreateBucketRequestDTO to a Bucket entity, ignoring the 'id' field during mapping.
    @Mapping(target = "Bucket.id", ignore = true)
    // id field is ignored during object creation
    Bucket toBucket(CreateBucketRequestDTO dto);

    // Converts UpdateBucketRequestDTO to a Bucket entity.
    Bucket toBucket(UpdateBucketRequestDTO dto);

    // Converts a MunicipalityDTO to a Municipality entity.
    Municipality toMunicipality(MunicipalityDTO dto);

    // Converts a ContainerDTO to a Container entity.
    Container toContainer(ContainerDTO dto);

    // Converts a BucketMunicipality entity to a BucketMunicipalityDTO.
    BucketMunicipalityDTO toDTO(BucketMunicipality bucketMunicipality);

    // Converts a list of BucketMunicipality entities to a list of BucketMunicipalityDTOs.
    List<BucketMunicipalityDTO> toDTOList(List<BucketMunicipality> list);

    // Converts a Municipality entity to a MunicipalityDTO.
    MunicipalityDTO toMunicipalityDTO(Municipality municipality);

    // Converts a BucketMunicipality entity to CreateBucketAssociationResponseDTO.
    CreateBucketAssociationResponseDTO toCreateBucketAssociationResponseDTO(BucketMunicipality association);
}
