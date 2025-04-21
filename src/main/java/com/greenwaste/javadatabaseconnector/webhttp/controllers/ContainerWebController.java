package com.greenwaste.javadatabaseconnector.webhttp.controllers;


import com.greenwaste.javadatabaseconnector.dtos.container.*;
import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import com.greenwaste.javadatabaseconnector.service.ContainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/containers")
public class ContainerWebController {

    private final ContainerService containerService;
    private final com.greenwaste.javadatabaseconnector.mapper.ContainerDTOMapper containerDTOMapper;

    public ContainerWebController(ContainerService containerService, com.greenwaste.javadatabaseconnector.mapper.ContainerDTOMapper containerDTOMapper) {
        this.containerService = containerService;
        this.containerDTOMapper = containerDTOMapper;
    }

    @PostMapping("/get")
    public ResponseEntity<GetContainerByIdResponseDTO> getContainerById(@RequestBody GetContainerByIdRequestDTO requestDTO) {
        var container = containerService.getContainerById(requestDTO.getId());
        var responseDTO = containerDTOMapper.toGetContainerByIdResponseDTO(container);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<GetAllContainersResponseDTO> getAllContainers() {
        var containers = containerService.getAllContainers();
        var responseDTO = containerDTOMapper.toGetAllContainersResponseDTO(containers);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<CreateContainerResponseDTO> createContainer(@RequestBody CreateContainerRequestDTO requestDTO) {
        var container = containerDTOMapper.toContainer(requestDTO);
        var saved = containerService.createContainer(container);
        var responseDTO = containerDTOMapper.toCreateContainerResponseDTO(saved);
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
        var response = containerDTOMapper.toContainerUnloadingResponseDTO(unloading);
        return ResponseEntity.ok(response);
    }
}



    /*
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        return ResponseEntity.ok(containerMapper.mapToContainerListDTO(containerService.getAllContainers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(containerMapper.mapToContainerDTO(containerService.getContainerById(id)));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody ContainerDTO dto) {
        Container created = containerService.createContainer(containerMapper.fromCreateContainerDTO(dto));
        return ResponseEntity.ok(containerMapper.mapToContainerDTO(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable Long id,
            @Valid @RequestBody ContainerDTO dto) {
        Container toUpdate = containerMapper.fromUpdateContainerDTO(dto);
        toUpdate.setId(id);
        containerService.updateContainer(toUpdate);
        Container updated = containerService.getContainerById(id);
        return ResponseEntity.ok(containerMapper.mapToContainerDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        containerService.deleteContainer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{containerId}/unload/{smasId}")
    public ResponseEntity<Map<String, Object>> unload(
            @PathVariable Long containerId,
            @PathVariable Long smasId) {
        ContainerUnloading u = containerService.containerUnloading(smasId, containerId);
        return ResponseEntity.ok(containerMapper.mapToUnloadingDTO(u));
    }


     */


