package com.greenwaste.javadatabaseconnector.mapper;

import com.greenwaste.javadatabaseconnector.dtos.base.*;
import com.greenwaste.javadatabaseconnector.model.*;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    // Conversores para DTOs simples
    UserDTO toUserDTO(User user);
    AdminDTO toAdminDTO(Admin admin);
    SmasDTO toSmasDTO(Smas smas);
    MunicipalityDTO toMunicipalityDTO(Municipality municipality);
    BucketDTO toBucketDTO(Bucket bucket);
    ContainerDTO toContainerDTO(Container container);

    // Conversores de listas
    default Map<String, Object> mapToAdminList(List<Admin> list) {
        return Map.of("admins", list.stream().map(this::toAdminDTO).toList());
    }

    default Map<String, Object> mapToSmasList(List<Smas> list) {
        return Map.of("smas", list.stream().map(this::toSmasDTO).toList());
    }

    default Map<String, Object> mapToMunicipalityList(List<Municipality> list) {
        return Map.of("municipalities", list.stream().map(this::toMunicipalityDTO).toList());
    }

    default Map<String, Object> mapToBucketList(List<Bucket> list) {
        return Map.of("buckets", list.stream().map(this::toBucketDTO).toList());
    }

    default Map<String, Object> mapToContainerList(List<Container> list) {
        return Map.of("containers", list.stream().map(this::toContainerDTO).toList());
    }
}
