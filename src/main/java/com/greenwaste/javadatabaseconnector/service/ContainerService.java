package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.repository.ContainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }

    public Container getContainerById(Long id) {
        return containerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Container not found"));
    }

    @Transactional
    public Container createContainer(Container container) {
        return containerRepository.save(container);
    }

    @Transactional
    public Container updateContainer(Long id, Container updatedContainer) {
        Container existing = getContainerById(id);
        existing.setCapacity(updatedContainer.getCapacity());
        existing.setLocalization(updatedContainer.getLocalization());
        existing.setCurrentVolumeLevel(updatedContainer.getCurrentVolumeLevel());
        return containerRepository.save(existing);
    }

    @Transactional
    public void deleteContainer(Long id) {
        containerRepository.deleteById(id);
    }
}
