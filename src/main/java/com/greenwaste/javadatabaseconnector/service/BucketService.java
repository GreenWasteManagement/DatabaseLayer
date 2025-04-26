package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.dtos.bucket.request.BucketFullUpdateRequestDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.BucketFullUpdateResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.BucketMunicipalityContainerCountResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.GetActiveBucketMunicipalityAssociationsResponseDTO;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BucketService {

    private final BucketRepository bucketRepository;
    private final BucketMunicipalityRepository bucketMunicipalityRepository;
    private final BucketMunicipalityContainerRepository bucketMunicipalityContainerRepository;
    private final ContainerRepository containerRepository;
    private final MunicipalityRepository municipalityRepository;

    public BucketService(BucketRepository bucketRepository, BucketMunicipalityRepository bucketMunicipalityRepository, BucketMunicipalityContainerRepository bucketMunicipalityContainerRepository, ContainerRepository containerRepository, MunicipalityRepository municipalityRepository) {
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
        return bucketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bucket not found with id: " + id));
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

    /*
     * Deposit
     */
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
    public void createBucketAssociation(Long bucketId, Long municipalityId) {
        Bucket bucket = bucketRepository.findById(bucketId).orElseThrow(() -> new EntityNotFoundException("Bucket not found"));
        Municipality municipality = municipalityRepository.findById(municipalityId).orElseThrow(() -> new EntityNotFoundException("Municipality not found"));

        List<BucketMunicipality> activeBuckets = bucketMunicipalityRepository.findByBucketIdAndStatus(bucketId, true);

        if (activeBuckets.isEmpty()) {
            Long userId = municipality.getId();

            List<BucketMunicipality> activeAssociationsOfUser = bucketMunicipalityRepository.findByUserIdAndStatus(userId, Boolean.TRUE);

            for (BucketMunicipality association : activeAssociationsOfUser) {
                association.setStatus(Boolean.FALSE);
                bucketMunicipalityRepository.save(association);
            }

            BucketMunicipality newBucketAssociation = new BucketMunicipality();
            newBucketAssociation.setBucket(bucket);
            newBucketAssociation.setUser(municipality);
            newBucketAssociation.setTimestampOfAssociation(Instant.now());
            newBucketAssociation.setStatus(Boolean.TRUE);
            bucketMunicipalityRepository.save(newBucketAssociation);

            bucket.setIsAssociated(Boolean.TRUE);
            bucketRepository.save(bucket);
        }

    }

    @Transactional
    public void createDepositByIds(Long municipalityId, Long containerId, BigDecimal depositAmount) {
        Municipality municipality = municipalityRepository.findById(municipalityId).orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        Container container = containerRepository.findById(containerId).orElseThrow(() -> new EntityNotFoundException("Container not found"));
        createDeposit(municipality, container, depositAmount);
    }

    @Transactional
    public BucketMunicipality createBucketAssociationAndGet(Long bucketId, Long municipalityId) {
        Bucket bucket = bucketRepository.findById(bucketId).orElseThrow(() -> new EntityNotFoundException("Bucket not found"));
        Municipality municipality = municipalityRepository.findById(municipalityId).orElseThrow(() -> new EntityNotFoundException("Municipality not found"));


        bucketMunicipalityRepository.findByBucketIdAndStatus(bucketId, true).forEach(a -> {
            a.setStatus(false);
            bucketMunicipalityRepository.save(a);
        });

        BucketMunicipality newAssoc = new BucketMunicipality();
        newAssoc.setBucket(bucket);
        newAssoc.setUser(municipality);
        newAssoc.setTimestampOfAssociation(Instant.now());
        newAssoc.setStatus(true);
        bucketMunicipalityRepository.save(newAssoc);


        bucket.setIsAssociated(true);
        bucketRepository.save(bucket);

        return newAssoc;
    }


    public BucketMunicipalityContainerCountResponseDTO countAllBucketMunicipalityContainers() {
        Long total = bucketMunicipalityContainerRepository.countAll();
        BucketMunicipalityContainerCountResponseDTO dto = new BucketMunicipalityContainerCountResponseDTO();
        dto.setCount(total);
        return dto;
    }

    public List<GetActiveBucketMunicipalityAssociationsResponseDTO> getActiveAssociations() {

        ModelMapper modelMapper = new ModelMapper();

        List<BucketMunicipality> entities = bucketMunicipalityRepository.findByStatusTrue();

        return entities.stream().map(entity -> modelMapper.map(entity, GetActiveBucketMunicipalityAssociationsResponseDTO.class)).toList();
    }

    @Transactional(readOnly = true)
    public List<BucketMunicipalityContainer> getAllDepositsForMunicipality() {
        return bucketMunicipalityContainerRepository.findAllByMunicipalityIdWithActiveAssociation();
    }

    @Transactional(readOnly = true)
    public List<Bucket> getAllBucketsWithMunicipalityInfo() {
        return bucketRepository.findAllBucketsWithMunicipalityInfo();
    }

    @Transactional
    public BucketFullUpdateResponseDTO updateBucket(BucketFullUpdateRequestDTO dto) {
        Optional<Bucket> bucketOpt = bucketRepository.findById(dto.getBucketId());
        if (bucketOpt.isEmpty()) {
            return null;
        }

        Bucket bucket = bucketOpt.get();

        if (dto.getCapacity() != null && !dto.getCapacity().equals(bucket.getCapacity())) {
            bucket.setCapacity(dto.getCapacity());
        }
        if (dto.getIsAssociated() != null && !dto.getIsAssociated().equals(bucket.getIsAssociated())) {
            bucket.setIsAssociated(dto.getIsAssociated());
        }

        if (dto.getBucketMunicipalities() != null) {
            for (var bmDto : dto.getBucketMunicipalities()) {
                bucketMunicipalityRepository.findById(bmDto.getId()).ifPresent(bm -> {
                    if (bmDto.getStatus() != null && !bmDto.getStatus().equals(bm.getStatus())) {
                        bm.setStatus(bmDto.getStatus());
                    }

                    Municipality m = bm.getUser();
                    var mDto = bmDto.getMunicipality();
                    if (mDto != null) {
                        if (mDto.getNif() != null && !mDto.getNif().equals(m.getNif())) {
                            m.setNif(mDto.getNif());
                        }
                        if (mDto.getCitizenCardCode() != null && !mDto.getCitizenCardCode().equals(m.getCitizenCardCode())) {
                            m.setCitizenCardCode(mDto.getCitizenCardCode());
                        }

                        var uDto = mDto.getUser();
                        if (uDto != null) {
                            User user = m.getUser();
                            if (uDto.getName() != null && !uDto.getName().equals(user.getName())) {
                                user.setName(uDto.getName());
                            }
                            if (uDto.getEmail() != null && !uDto.getEmail().equals(user.getEmail())) {
                                user.setEmail(uDto.getEmail());
                            }
                        }
                    }
                });
            }
        }

        bucketRepository.save(bucket);

        var resp = new BucketFullUpdateResponseDTO();
        resp.setBucketId(bucket.getId());
        resp.setCapacity(bucket.getCapacity());
        resp.setIsAssociated(bucket.getIsAssociated());

        var list = bucket.getBucketMunicipalities().stream().map(bm -> {
            var r = new BucketFullUpdateResponseDTO.BucketMunicipalityResponse();
            r.setId(bm.getId());
            r.setStatus(bm.getStatus());

            Municipality m = bm.getUser();
            var mr = new BucketFullUpdateResponseDTO.MunicipalityResponse();
            mr.setId(m.getId());
            mr.setNif(m.getNif());
            mr.setCitizenCardCode(m.getCitizenCardCode());

            var ur = new BucketFullUpdateResponseDTO.UserResponse();
            ur.setId(m.getUser().getId());
            ur.setName(m.getUser().getName());
            ur.setEmail(m.getUser().getEmail());
            mr.setUser(ur);

            r.setMunicipality(mr);
            return r;
        }).collect(Collectors.toList());

        resp.setBucketMunicipalities(list);
        return resp;
    }

}
/*
 @Transactional
    public BucketMunicipalityContainer createDepositAndGet(Long municipalityId, Long containerId, BigDecimal amount) {
        Municipality municipality = municipalityRepository.findById(municipalityId)
                .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        Container container = containerRepository.findById(containerId)
                .orElseThrow(() -> new EntityNotFoundException("Container not found"));

        BucketMunicipality assoc = bucketMunicipalityRepository
                .findFirstByUserAndStatusTrue(municipality)
                .orElseThrow(() -> new EntityNotFoundException("Active bucket association not found"));


        if (amount.compareTo(assoc.getBucket().getCapacity()) > 0) {
            throw new IllegalArgumentException("Deposit exceeds bucket capacity");
        }
        BigDecimal newLevel = container.getCurrentVolumeLevel().add(amount);
        if (newLevel.compareTo(container.getCapacity()) > 0) {
            throw new IllegalArgumentException("Deposit exceeds container capacity");
        }

        BucketMunicipalityContainer deposit = new BucketMunicipalityContainer();
        deposit.setAssociation(assoc);
        deposit.setContainer(container);
        deposit.setDepositAmount(amount);
        deposit.setDepositTimestamp(Instant.now());
        bucketMunicipalityContainerRepository.save(deposit);

        container.setCurrentVolumeLevel(newLevel);
        containerRepository.save(container);

        return deposit;
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

 */


