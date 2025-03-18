package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipalityContainer;
import com.greenwaste.javadatabaseconnector.repository.BucketMunicipalityContainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BucketMunicipalityContainerService {

    private final BucketMunicipalityContainerRepository repository;

    public BucketMunicipalityContainerService(BucketMunicipalityContainerRepository repository) {
        this.repository = repository;
    }

    public List<BucketMunicipalityContainer> getAllDeposits() {
        return repository.findAll();
    }

    public BucketMunicipalityContainer getDepositById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Deposit not found"));
    }

    @Transactional
    public BucketMunicipalityContainer createDeposit(BucketMunicipalityContainer deposit) {
        return repository.save(deposit);
    }

    @Transactional
    public BucketMunicipalityContainer updateDeposit(Long id, BucketMunicipalityContainer updatedDeposit) {
        BucketMunicipalityContainer existing = getDepositById(id);
        existing.setAssociation(updatedDeposit.getAssociation());
        existing.setContainer(updatedDeposit.getContainer());
        existing.setDepositAmount(updatedDeposit.getDepositAmount());
        existing.setDepositTimestamp(updatedDeposit.getDepositTimestamp());
        return repository.save(existing);
    }

    @Transactional
    public void deleteDeposit(Long id) {
        repository.deleteById(id);
    }
}
