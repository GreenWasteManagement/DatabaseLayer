package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.dtos.bucketwebdto.older.GetBucketMunicipalityDTO;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.repository.*;
import jakarta.persistence.EntityNotFoundException;
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
    private final MunicipalityRepository municipalityRepository;

    public BucketService(BucketRepository bucketRepository,
                         BucketMunicipalityRepository bucketMunicipalityRepository,
                         BucketMunicipalityContainerRepository bucketMunicipalityContainerRepository,
                         ContainerRepository containerRepository, MunicipalityRepository municipalityRepository) {
        this.bucketRepository = bucketRepository;
        this.bucketMunicipalityRepository = bucketMunicipalityRepository;
        this.bucketMunicipalityContainerRepository = bucketMunicipalityContainerRepository;
        this.containerRepository = containerRepository;
        this.municipalityRepository = municipalityRepository;
    }

    /*
     * Bucket
     */
    @Transactional(readOnly = true)
    public Bucket getBucketById(Long id) {
        return bucketRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bucket not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Bucket> getAllBuckets() {
        return bucketRepository.findAll();
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

            if (updatedBucket.getCapacity() != null &&
                    !updatedBucket.getCapacity().equals(existingBucket.getCapacity())) {
                existingBucket.setCapacity(updatedBucket.getCapacity());
            }

            if (updatedBucket.getIsAssociated() != null &&
                    !updatedBucket.getIsAssociated().equals(existingBucket.getIsAssociated())) {
                existingBucket.setIsAssociated(updatedBucket.getIsAssociated());
            }

            bucketRepository.save(existingBucket);
        }
    }

    @Transactional
    public void deleteBucket(Long id) {
        bucketRepository.deleteById(id);
    }

    /*
     * Depósito
     */
    @Transactional
    public void createDeposit(Municipality municipalityDeposit, Container containerDeposit, BigDecimal depositAmount) {
        Optional<BucketMunicipality> bucketAssociationOpt =
                bucketMunicipalityRepository.findFirstByUserAndStatusTrue(municipalityDeposit);

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

    /*
     * Bucket-Municipality
     */


    @Transactional(readOnly = true)
    public List<BucketMunicipality> getAllBucketMunicipalities() {
        return bucketMunicipalityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<BucketMunicipality> getBucketMunicipalityById(Long id) {
        return bucketMunicipalityRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<BucketMunicipality> getBucketMunicipalitiesByUserIdAndStatus(Long userId, Boolean status) {
        return bucketMunicipalityRepository.findByUserIdAndStatus(userId, status);
    }

    @Transactional(readOnly = true)
    public List<BucketMunicipality> getBucketMunicipalitiesByBucketIdAndStatus(Long bucketId, Boolean status) {
        return bucketMunicipalityRepository.findByBucketIdAndStatus(bucketId, status);
    }

    @Transactional(readOnly = true)
    public Optional<BucketMunicipality> getFirstByUserAndStatusTrue(Municipality user) {
        return bucketMunicipalityRepository.findFirstByUserAndStatusTrue(user);
    }

    @Transactional
    public void createBucketAssociation(Bucket bucketToAssociate, Municipality municipalityToAssociate) {
        Long bucketId = bucketToAssociate.getId();

        List<BucketMunicipality> activeBuckets =
                bucketMunicipalityRepository.findByBucketIdAndStatus(bucketId, true);

        if (activeBuckets.isEmpty()) {
            Long userId = municipalityToAssociate.getId();

            List<BucketMunicipality> activeAssociationsOfUser =
                    bucketMunicipalityRepository.findByUserIdAndStatus(userId, Boolean.TRUE);

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
    public void createBucketAssociationByIds(Long bucketId, Long municipalityId) {
        Bucket bucket = bucketRepository.findById(bucketId)
                .orElseThrow(() -> new EntityNotFoundException("Bucket not found"));
        Municipality municipality = municipalityRepository.findById(municipalityId)
                .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        createBucketAssociation(bucket, municipality);
    }

    @Transactional
    public void createDepositByIds(Long municipalityId, Long containerId, BigDecimal depositAmount) {
        Municipality municipality = municipalityRepository.findById(municipalityId)
                .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new EntityNotFoundException("Container not found"));
        createDeposit(municipality, container, depositAmount);
    }

    public GetBucketMunicipalityDTO convertToDTO(BucketMunicipality entity) {
        return new GetBucketMunicipalityDTO(
                entity.getId(),
                entity.getBucket().getId(),
                entity.getUser().getId(),
                entity.getTimestampOfAssociation(),
                entity.getStatus()
        );
    }

    public List<GetBucketMunicipalityDTO> convertToDTOList(List<BucketMunicipality> list) {
        return list.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
