package com.greenwaste.javadatabaseconnector.mapper;

import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.ContainerUnloadingDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import com.greenwaste.javadatabaseconnector.dtos.container.request.CreateContainerRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.container.response.ContainerUnloadingResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.container.response.CreateContainerResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.container.response.GetAllContainersResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.container.response.GetContainerByIdResponseDTO;
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

    // Base entity to DTO
    ContainerDTO toContainerDTO(Container container);

    SmasDTO toSmasDTO(Smas smas);

    ContainerUnloadingDTO toContainerUnloadingDTO(ContainerUnloading unloading);

    // DTO to entity
    @Mapping(target = "Container.id", ignore = true)
    @Mapping(target = "Container.currentVolumeLevel", constant = "0")
    Container fromCreateRequest(CreateContainerRequestDTO dto);

    // Convert ContainerDTO to Container (for update)
    Container toContainer(ContainerDTO dto);

    // Response wrappers
    default CreateContainerResponseDTO toCreateResponse(Container container) {
        return new CreateContainerResponseDTO(toContainerDTO(container));
    }

    default GetContainerByIdResponseDTO toGetByIdResponse(Container container) {
        return new GetContainerByIdResponseDTO(toContainerDTO(container));
    }

    default GetAllContainersResponseDTO toGetAllResponse(List<Container> containers) {
        List<ContainerDTO> dtos = containers.stream().map(this::toContainerDTO).toList();
        return new GetAllContainersResponseDTO(dtos);
    }

    default ContainerUnloadingResponseDTO toUnloadingResponse(ContainerUnloading unloading) {
        ContainerUnloadingResponseDTO response = new ContainerUnloadingResponseDTO();
        response.setUnloadedQuantity(unloading.getUnloadedQuantity());
        response.setUnloadingTimestamp(unloading.getUnloadingTimestamp());
        response.setUser(toSmasDTO(unloading.getUser()));
        response.setContainer(toContainerDTO(unloading.getContainer()));
        return response;
    }
}
