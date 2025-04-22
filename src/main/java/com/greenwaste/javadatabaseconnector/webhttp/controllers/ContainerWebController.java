package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.container.request.*;
import com.greenwaste.javadatabaseconnector.dtos.container.response.*;
import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import com.greenwaste.javadatabaseconnector.service.ContainerService;
import com.greenwaste.javadatabaseconnector.mapper.ContainerDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/containers")
public class ContainerWebController {

    private final ContainerService containerService;
    private final ContainerDTOMapper containerDTOMapper;

    public ContainerWebController(ContainerService containerService, ContainerDTOMapper containerDTOMapper) {
        this.containerService = containerService;
        this.containerDTOMapper = containerDTOMapper;
    }

    @PostMapping("/get")
    public ResponseEntity<GetContainerByIdResponseDTO> getContainerById(@RequestBody GetContainerByIdRequestDTO requestDTO) {
        var container = containerService.getContainerById(requestDTO.getId());
        var responseDTO = containerDTOMapper.toGetByIdResponse(container);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<GetAllContainersResponseDTO> getAllContainers() {
        var containers = containerService.getAllContainers();
        var responseDTO = containerDTOMapper.toGetAllResponse(containers);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<CreateContainerResponseDTO> createContainer(@RequestBody CreateContainerRequestDTO requestDTO) {
        var container = containerDTOMapper.fromCreateRequest(requestDTO);
        var saved = containerService.createContainer(container);
        var responseDTO = containerDTOMapper.toCreateResponse(saved);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateContainerResponseDTO> updateContainer(@RequestBody UpdateContainerRequestDTO dto) {
        var containerToUpdate = containerDTOMapper.toContainer(dto.getContainer());
        containerService.updateContainer(containerToUpdate);
        return ResponseEntity.ok(new UpdateContainerResponseDTO("Container updated successfully."));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteContainerResponseDTO> deleteContainer(@RequestBody DeleteContainerRequestDTO dto) {
        containerService.deleteContainer(dto.getId());
        return ResponseEntity.ok(new DeleteContainerResponseDTO("Container deleted successfully."));
    }

    @PostMapping("/unloading")
    public ResponseEntity<ContainerUnloadingResponseDTO> containerUnloading(@RequestBody ContainerUnloadingRequestDTO dto) {
        ContainerUnloading unloading = containerService.containerUnloading(dto.getSmasId(), dto.getContainerId());
        var response = containerDTOMapper.toUnloadingResponse(unloading);
        return ResponseEntity.ok(response);
    }
}
