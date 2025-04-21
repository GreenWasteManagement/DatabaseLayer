package com.greenwaste.javadatabaseconnector.mapper;

import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import com.greenwaste.javadatabaseconnector.dtos.container.*;
import com.greenwaste.javadatabaseconnector.dtos.older.containerwebdto.UpdateContainerRequestDTO;
import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import com.greenwaste.javadatabaseconnector.model.Smas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContainerDTOMapper {

    ContainerDTOMapper INSTANCE = Mappers.getMapper(ContainerDTOMapper.class);

    // BASE CONVERSÕES

    ContainerDTO toContainerDTO(Container container);

    SmasDTO toSmasDTO(Smas smas);

    // REQUESTS

    @Mapping(target = "id", ignore = true)
    Container toContainer(CreateContainerRequestDTO dto);


    // RESPONSE COMPOSTOS

    @Mapping(source = "container", target = "container")
    CreateContainerResponseDTO toCreateContainerResponseDTO(Container container);

    @Mapping(source = "container", target = "container")
    GetContainerByIdResponseDTO toGetContainerByIdResponseDTO(Container container);

    @Mapping(source = "containerList", target = "containers")
    GetAllContainersResponseDTO toGetAllContainersResponseDTO(List<Container> containerList);

    // UNLOADING

    @Mapping(source = "user", target = "user")
    @Mapping(source = "container", target = "container")
    ContainerUnloadingResponseDTO toContainerUnloadingResponseDTO(ContainerUnloading unloading);

    // UPDATE

    @Mapping(target = "id", source = "container.id")
    @Mapping(target = "capacity", source = "container.capacity")
    @Mapping(target = "localization", source = "container.localization")
    @Mapping(target = "currentVolumeLevel", source = "container.currentVolumeLevel")
    Container toContainer(UpdateContainerRequestDTO dto);

    Container toContainer(ContainerDTO container);
}