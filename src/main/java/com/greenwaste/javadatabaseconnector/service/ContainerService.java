package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import com.greenwaste.javadatabaseconnector.model.Smas;
import com.greenwaste.javadatabaseconnector.service.repository.ContainerRepository;
import com.greenwaste.javadatabaseconnector.service.repository.ContainerUnloadingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;
    private final ContainerUnloadingRepository containerUnloadingRepository;

    public ContainerService(ContainerRepository containerRepository,
                            ContainerUnloadingRepository containerUnloadingRepository) {
        this.containerRepository = containerRepository;
        this.containerUnloadingRepository = containerUnloadingRepository;
    }

    @Transactional(readOnly = true)
    public Container getContainerById(Long id) {
        return containerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Container not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }

    @Transactional
    public Container createContainer(Container container) {
        return containerRepository.save(container);
    }


    @Transactional
    public void updateContainer(Container updatedContainer) {
        Optional<Container> existingOpt = containerRepository.findById(updatedContainer.getId());
        if (existingOpt.isPresent()) {
            Container existing = existingOpt.get();
            if (updatedContainer.getCapacity() != null && !updatedContainer.getCapacity().equals(existing.getCapacity())) {
                existing.setCapacity(updatedContainer.getCapacity());
            }
            if (updatedContainer.getLocalization() != null && !updatedContainer.getLocalization().equals(existing.getLocalization())) {
                existing.setLocalization(updatedContainer.getLocalization());
            }
            if (updatedContainer.getCurrentVolumeLevel() != null &&
                    !updatedContainer.getCurrentVolumeLevel().equals(existing.getCurrentVolumeLevel())) {
                existing.setCurrentVolumeLevel(updatedContainer.getCurrentVolumeLevel());
            }
            containerRepository.save(existing);
        }


    }

    @Transactional
    public void deleteContainer(Long id) {
        containerRepository.deleteById(id);
    }

    @Transactional
    public void containerUnloading(Smas smasUnload, Container containerUnload) {

        if (containerUnload.getCurrentVolumeLevel().compareTo(BigDecimal.ZERO) > 0) {

            ContainerUnloading containerUnloaded = new ContainerUnloading();
            containerUnloaded.setUnloadedQuantity(containerUnload.getCurrentVolumeLevel());
            containerUnloaded.setUnloadingTimestamp(Instant.now());
            containerUnloaded.setUser(smasUnload);
            containerUnloaded.setContainer(containerUnload);
            containerUnloadingRepository.save(containerUnloaded);

            containerUnload.setCurrentVolumeLevel(BigDecimal.ZERO);
            containerRepository.save(containerUnload);


        }
    }
}
