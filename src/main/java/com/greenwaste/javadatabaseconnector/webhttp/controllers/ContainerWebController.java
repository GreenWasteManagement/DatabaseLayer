package com.greenwaste.javadatabaseconnector.webhttp.controllers;


import com.greenwaste.javadatabaseconnector.dtos.base.ContainerDTO;
import com.greenwaste.javadatabaseconnector.mapper.ContainerDTOMapper;
import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import com.greenwaste.javadatabaseconnector.service.ContainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/containers")
public class ContainerWebController {

    private final ContainerService containerService;
    private final ContainerDTOMapper containerMapper;

    public ContainerWebController(ContainerService containerService, @Qualifier("containerDTOMapperImpl") ContainerDTOMapper containerMapper) {
        this.containerService = containerService;
        this.containerMapper = containerMapper;
    }

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
}

