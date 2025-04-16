package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.repository.ContainerRepository;
import com.greenwaste.javadatabaseconnector.repository.ContainerUnloadingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
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
