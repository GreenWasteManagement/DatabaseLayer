package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.container.request.*;
import com.greenwaste.javadatabaseconnector.dtos.container.response.*;
import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import com.greenwaste.javadatabaseconnector.service.ContainerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class ContainerWebController {

    private final ContainerService containerService;

    public ContainerWebController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @PostMapping("/get")
    public ResponseEntity<GetContainerByIdResponseDTO> getContainerById(@RequestBody GetContainerByIdRequestDTO requestDTO) {
        var container = containerService.getContainerById(requestDTO.getId());

        ModelMapper modelMapper = new ModelMapper();
        GetContainerByIdResponseDTO.Container containerDTO = modelMapper.map(container, GetContainerByIdResponseDTO.Container.class);

        GetContainerByIdResponseDTO responseDTO = new GetContainerByIdResponseDTO();
        responseDTO.setContainer(containerDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<GetAllContainersResponseDTO> getAllContainers() {
        var containers = containerService.getAllContainers();
        var bucketMunicipalityContainers = containerService.getAllBucketMunicipalityContainers();
        var containerUnloadings = containerService.getAllContainerUnloadings();

        ModelMapper modelMapper = new ModelMapper();

        List<GetAllContainersResponseDTO.Container> containerDTOs = containers.stream().map(container -> {
            GetAllContainersResponseDTO.Container containerDTO = modelMapper.map(container, GetAllContainersResponseDTO.Container.class);

            List<GetAllContainersResponseDTO.BucketMunicipalityContainer> bmcDTOs = bucketMunicipalityContainers.stream().filter(bmc -> bmc.getContainer().getId().equals(container.getId())).map(bmc -> modelMapper.map(bmc, GetAllContainersResponseDTO.BucketMunicipalityContainer.class)).toList();

            List<GetAllContainersResponseDTO.ContainerUnloading> unloadingDTOs = containerUnloadings.stream().filter(unloading -> unloading.getContainer().getId().equals(container.getId())).map(unloading -> modelMapper.map(unloading, GetAllContainersResponseDTO.ContainerUnloading.class)).toList();

            containerDTO.setBucketMunicipalityContainers(bmcDTOs);
            containerDTO.setContainerUnloadings(unloadingDTOs);

            return containerDTO;
        }).toList();

        GetAllContainersResponseDTO responseDTO = new GetAllContainersResponseDTO();
        responseDTO.setContainers(containerDTOs);

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping
    public ResponseEntity<CreateContainerResponseDTO> createContainer(@RequestBody CreateContainerRequestDTO requestDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Container container = modelMapper.map(requestDTO, Container.class);

        Container saved = containerService.createContainer(container);

        CreateContainerResponseDTO.Container responseContainer = modelMapper.map(saved, CreateContainerResponseDTO.Container.class);
        CreateContainerResponseDTO responseDTO = new CreateContainerResponseDTO();
        responseDTO.setContainer(responseContainer);

        return ResponseEntity.ok(responseDTO);
    }


    @PutMapping("/update")
    public ResponseEntity<UpdateContainerResponseDTO> updateContainer(@RequestBody UpdateContainerRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        Container containerToUpdate = modelMapper.map(dto.getContainer(), Container.class);

        containerService.updateContainer(containerToUpdate);

        UpdateContainerResponseDTO responseDTO = new UpdateContainerResponseDTO();
        responseDTO.setMessage("Container updated successfully.");
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteContainerResponseDTO> deleteContainer(@RequestBody DeleteContainerRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();


        Long id = dto.getId();

        containerService.deleteContainer(id);

        DeleteContainerResponseDTO responseDTO = new DeleteContainerResponseDTO();
        responseDTO.setMessage("Container deleted successfully.");
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/unloading")
    public ResponseEntity<ContainerUnloadingResponseDTO> containerUnloading(@RequestBody ContainerUnloadingRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        Long smasId = dto.getSmasId();
        Long containerId = dto.getContainerId();

        ContainerUnloading unloading = containerService.containerUnloading(smasId, containerId);

        ContainerUnloadingResponseDTO responseDTO = modelMapper.map(unloading, ContainerUnloadingResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }
}
