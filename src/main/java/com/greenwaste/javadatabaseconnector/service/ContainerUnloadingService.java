package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import com.greenwaste.javadatabaseconnector.repository.ContainerUnloadingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContainerUnloadingService {

    private final ContainerUnloadingRepository repository;

    public ContainerUnloadingService(ContainerUnloadingRepository repository) {
        this.repository = repository;
    }

    public List<ContainerUnloading> getAllUnloadings() {
        return repository.findAll();
    }

    public ContainerUnloading getUnloadingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unloading not found"));
    }

    @Transactional
    public ContainerUnloading createUnloading(ContainerUnloading unloading) {
        return repository.save(unloading);
    }

    @Transactional
    public ContainerUnloading updateUnloading(Long id, ContainerUnloading updatedUnloading) {
        ContainerUnloading existing = getUnloadingById(id);
        existing.setContainer(updatedUnloading.getContainer());
        existing.setUser(updatedUnloading.getUser());
        existing.setUnloadedQuantity(updatedUnloading.getUnloadedQuantity());
        existing.setUnloadingTimestamp(updatedUnloading.getUnloadingTimestamp());
        return repository.save(existing);
    }

    @Transactional
    public void deleteUnloading(Long id) {
        repository.deleteById(id);
    }
}
