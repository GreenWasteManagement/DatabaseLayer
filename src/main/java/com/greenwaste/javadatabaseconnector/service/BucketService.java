package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.repository.BucketMunicipalityContainerRepository;
import com.greenwaste.javadatabaseconnector.repository.BucketMunicipalityRepository;
import com.greenwaste.javadatabaseconnector.repository.BucketRepository;
import com.greenwaste.javadatabaseconnector.repository.ContainerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class BucketService {

    private final BucketRepository bucketRepository;
    private final BucketMunicipalityRepository bucketMunicipalityRepository;
    private final BucketMunicipalityContainerRepository bucketMunicipalityContainerRepository;
    private final ContainerRepository containerRepository;

    public BucketService(BucketRepository bucketRepository,
                         BucketMunicipalityRepository bucketMunicipalityRepository,
                         BucketMunicipalityContainerRepository bucketMunicipalityContainerRepository, ContainerRepository containerRepository) {
        this.bucketRepository = bucketRepository;
        this.bucketMunicipalityRepository = bucketMunicipalityRepository;
        this.bucketMunicipalityContainerRepository = bucketMunicipalityContainerRepository;
        this.containerRepository = containerRepository;
    }


    @Transactional
    public Bucket createBucket(Bucket bucket) {
        return bucketRepository.save(bucket);
    }

    @Transactional
    public void updateBucket(Bucket updatedBucket) {
        Optional<Bucket> existingBucketOpt = bucketRepository.findById(updatedBucket.getId());

        if (existingBucketOpt.isPresent()) {
            Bucket existingBucket = existingBucketOpt.get();
            if (updatedBucket.getCapacity() != null && !updatedBucket.getCapacity().equals(existingBucket.getCapacity())) {
                existingBucket.setCapacity(updatedBucket.getCapacity());
            }
            if (updatedBucket.getIsAssociated() != null && !updatedBucket.getIsAssociated().equals(existingBucket.getIsAssociated())) {
                existingBucket.setIsAssociated(updatedBucket.getIsAssociated());
            }
            bucketRepository.save(existingBucket);
        }
    }

    @Transactional
    public void deleteBucket(Long id) {
        bucketRepository.deleteById(id);
    }


    // Cria a associação entre Bucket e Municipality
    @Transactional
    public void createBucketAssociation(Bucket bucketToAssociate, Municipality municipalityToAssociate) {

        // First verify if the bucket is free;

        // Second Verificate if the User has already any bucket, if does he can change but the other one will become inactive

        // Third add a new bucket association with the respectives id's,  the timestamps properly and the status in true

        Long bucketId = bucketToAssociate.getId();

        List<BucketMunicipality> activeBuckets = bucketMunicipalityRepository.findByBucketIdAndStatus(bucketId, true);

        if (activeBuckets.isEmpty()) {

            Long userId = municipalityToAssociate.getId();

            List<BucketMunicipality> activeAssociationsOfUser = bucketMunicipalityRepository
                    .findByUserIdAndStatus(userId, Boolean.TRUE);

            for (BucketMunicipality association : activeAssociationsOfUser) {
                association.setStatus(Boolean.FALSE);
                bucketMunicipalityRepository.save(association);
            }

            BucketMunicipality newBucketAssociation = new BucketMunicipality();
            newBucketAssociation.setBucket(bucketToAssociate);
            newBucketAssociation.setUser(municipalityToAssociate);
            newBucketAssociation.setTimestampOfAssociation(Instant.now());
            newBucketAssociation.setStatus(Boolean.TRUE);
            bucketMunicipalityRepository.save(newBucketAssociation);

            bucketToAssociate.setIsAssociated(Boolean.TRUE);

            bucketRepository.save(bucketToAssociate);

        }

    }

    @Transactional
    public void createDeposit(Municipality municipalityDeposit, Container containerDeposit, BigDecimal depositAmount) {
        Optional<BucketMunicipality> bucketAssociationOpt = bucketMunicipalityRepository.findFirstByUserAndStatusTrue(municipalityDeposit);

        if (bucketAssociationOpt.isPresent()) {

            BucketMunicipality bucketAssociation = bucketAssociationOpt.get();
            Bucket bucket = bucketAssociation.getBucket();

            if (depositAmount.compareTo(bucket.getCapacity()) <= 0) {

                BigDecimal novoVolume = containerDeposit.getCurrentVolumeLevel().add(depositAmount);
                if (novoVolume.compareTo(containerDeposit.getCapacity()) <= 0) {

                    BucketMunicipalityContainer deposit = new BucketMunicipalityContainer();
                    deposit.setAssociation(bucketAssociation);
                    deposit.setContainer(containerDeposit);
                    deposit.setDepositAmount(depositAmount);
                    deposit.setDepositTimestamp(Instant.now());
                    bucketMunicipalityContainerRepository.save(deposit);

                    containerDeposit.setCurrentVolumeLevel(novoVolume);
                    containerRepository.save(containerDeposit);

                }

            }

        }

    }
}
