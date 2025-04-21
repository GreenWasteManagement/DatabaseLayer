package com.greenwaste.javadatabaseconnector.mapper;


import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.ContainerUnloadingDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import com.greenwaste.javadatabaseconnector.model.Smas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ContainerDTOMapper {

    ContainerDTO toContainerDTO(Container container);

    SmasDTO toSmasDTO(Smas smas);

    ContainerUnloadingDTO toContainerUnloadingDTO(ContainerUnloading unloading);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentVolumeLevel", constant = "0")
    Container fromCreateContainerDTO(ContainerDTO dto);

    @Mapping(target = "currentVolumeLevel", source = "currentVolumeLevel")
    @Mapping(target = "localization", source = "localization")
    @Mapping(target = "capacity", source = "capacity")
    Container fromUpdateContainerDTO(ContainerDTO dto);

    default Map<String, Object> mapToContainerDTO(Container container) {
        ContainerDTO dto = toContainerDTO(container);
        boolean isFull = container.getCurrentVolumeLevel().compareTo(container.getCapacity()) >= 0;
        return Map.of("container", dto, "isFull", isFull);
    }

    /**
     * Mapeia lista de Containers para um Map dinâmico contendo a lista e total
     */
    default Map<String, Object> mapToContainerListDTO(List<Container> list) {
        List<ContainerDTO> dtos = list.stream().map(this::toContainerDTO).toList();
        return Map.of("containers", dtos, "total", dtos.size());
    }

    default Map<String, Object> mapToUnloadingDTO(ContainerUnloading unloading) {
        return Map.of("unloading", toContainerUnloadingDTO(unloading));
    }
}

