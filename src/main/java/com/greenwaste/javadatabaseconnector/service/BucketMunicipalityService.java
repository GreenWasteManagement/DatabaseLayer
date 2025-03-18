package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import com.greenwaste.javadatabaseconnector.repository.BucketMunicipalityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BucketMunicipalityService {

    private final BucketMunicipalityRepository bucketMunicipalityRepository;

    public BucketMunicipalityService(BucketMunicipalityRepository bucketMunicipalityRepository) {
        this.bucketMunicipalityRepository = bucketMunicipalityRepository;
    }

    public List<BucketMunicipality> getAllAssociations() {
        return bucketMunicipalityRepository.findAll();
    }

    public BucketMunicipality getAssociationById(Long id) {
        return bucketMunicipalityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Association not found"));
    }

    @Transactional
    public BucketMunicipality createAssociation(BucketMunicipality association) {
        return bucketMunicipalityRepository.save(association);
    }

    @Transactional
    public BucketMunicipality updateAssociation(Long id, BucketMunicipality updatedAssociation) {
        BucketMunicipality existing = getAssociationById(id);
        existing.setBucket(updatedAssociation.getBucket());
        existing.setUser(updatedAssociation.getUser());
        existing.setStatus(updatedAssociation.getStatus());
        existing.setTimestampOfAssociation(updatedAssociation.getTimestampOfAssociation());
        return bucketMunicipalityRepository.save(existing);
    }

    @Transactional
    public void deleteAssociation(Long id) {
        bucketMunicipalityRepository.deleteById(id);
    }
}
