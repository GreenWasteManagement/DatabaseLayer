package com.greenwaste.javadatabaseconnector.webhttp.controllers;


import com.greenwaste.javadatabaseconnector.dtos.containerwebdto.ContainerUnloadingRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.containerwebdto.CreateContainerRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.containerwebdto.UpdateContainerRequestDTO;
import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.Smas;
import com.greenwaste.javadatabaseconnector.repository.ContainerRepository;
import com.greenwaste.javadatabaseconnector.repository.SmasRepository;
import com.greenwaste.javadatabaseconnector.service.ContainerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/containers")
public class ContainerWebController {

    private final ContainerService containerService;
    private final ContainerRepository containerRepository;
    private final SmasRepository smasRepository;

    public ContainerWebController(ContainerService containerService,
                                  ContainerRepository containerRepository,
                                  SmasRepository smasRepository) {
        this.containerService = containerService;
        this.containerRepository = containerRepository;
        this.smasRepository = smasRepository;
    }

    @PostMapping
    public ResponseEntity<Container> createContainer(@Valid @RequestBody CreateContainerRequestDTO dto) {
        Container container = new Container();
        container.setCapacity(dto.getCapacity());
        container.setLocalization(dto.getLocalization());
        container.setCurrentVolumeLevel(BigDecimal.ZERO);
        return ResponseEntity.ok(containerService.createContainer(container));
    }

    @PutMapping
    public ResponseEntity<Void> updateContainer(@Valid @RequestBody UpdateContainerRequestDTO dto) {
        Container container = new Container();
        container.setId(dto.getId());
        container.setCapacity(dto.getCapacity());
        container.setLocalization(dto.getLocalization());
        container.setCurrentVolumeLevel(dto.getCurrentVolumeLevel());
        containerService.updateContainer(container);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContainer(@PathVariable Long id) {
        containerService.deleteContainer(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/unload")
    public ResponseEntity<Void> containerUnloading(@Valid @RequestBody ContainerUnloadingRequestDTO dto) {
        Container container = containerService.getContainerById(dto.getContainerId());
        Smas smas = smasRepository.findById(dto.getSmasId()).orElseThrow();
        containerService.containerUnloading(smas, container);
        return ResponseEntity.ok().build();
    }
}

