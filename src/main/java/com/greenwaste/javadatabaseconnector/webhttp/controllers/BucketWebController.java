package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.bucketwebdto.*;
import com.greenwaste.javadatabaseconnector.model.Bucket;
import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.Municipality;
import com.greenwaste.javadatabaseconnector.repository.BucketRepository;
import com.greenwaste.javadatabaseconnector.repository.ContainerRepository;
import com.greenwaste.javadatabaseconnector.repository.MunicipalityRepository;
import com.greenwaste.javadatabaseconnector.service.BucketService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buckets")
public class BucketWebController {

    private final BucketService bucketService;
    private final BucketRepository bucketRepository;
    private final MunicipalityRepository municipalityRepository;
    private final ContainerRepository containerRepository;

    public BucketWebController(BucketService bucketService,
                               BucketRepository bucketRepository,
                               MunicipalityRepository municipalityRepository,
                               ContainerRepository containerRepository) {
        this.bucketService = bucketService;
        this.bucketRepository = bucketRepository;
        this.municipalityRepository = municipalityRepository;
        this.containerRepository = containerRepository;
    }

    // ---------------------- BUCKETS CRUD ----------------------

    @GetMapping("/{id}")
    public ResponseEntity<GetBucketDTO> getBucketById(@PathVariable Long id) {
        Bucket bucket = bucketService.getBucketById(id);
        GetBucketDTO dto = new GetBucketDTO(bucket.getId(), bucket.getCapacity(), bucket.getIsAssociated());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<GetBucketDTO>> getAllBuckets() {
        List<Bucket> buckets = bucketService.getAllBuckets();
        List<GetBucketDTO> dtoList = buckets.stream()
                .map(b -> new GetBucketDTO(b.getId(), b.getCapacity(), b.getIsAssociated()))
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping
    public ResponseEntity<Bucket> createBucket(@Valid @RequestBody CreateBucketRequestDTO dto) {
        Bucket bucket = new Bucket();
        bucket.setCapacity(dto.getCapacity());
        bucket.setIsAssociated(false);
        Bucket savedBucket = bucketService.createBucket(bucket);
        return ResponseEntity.ok(savedBucket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBucket(@PathVariable Long id, @Valid @RequestBody UpdateBucketRequestDTO dto) {
        Bucket bucket = new Bucket();
        bucket.setId(id);
        bucket.setCapacity(dto.getCapacity());
        bucket.setIsAssociated(dto.getIsAssociated());
        bucketService.updateBucket(bucket);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBucket(@PathVariable Long id) {
        bucketService.deleteBucket(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------------- ASSOCIATIONS BETWEEN MUNICIPALITY AND BUCKETS ----------------------

    @PostMapping("/{bucketId}/associate/municipality/{municipalityId}")
    public ResponseEntity<Void> associateBucketToMunicipality(@PathVariable Long bucketId, @PathVariable Long municipalityId) {
        Bucket bucket = bucketRepository.findById(bucketId)
                .orElseThrow(() -> new EntityNotFoundException("Bucket not found"));
        Municipality municipality = municipalityRepository.findById(municipalityId)
                .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));

        bucketService.createBucketAssociation(bucket, municipality);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/associations")
    public ResponseEntity<List<GetBucketMunicipalityDTO>> getAllAssociations() {
        List<BucketMunicipality> all = bucketService.getAllBucketMunicipalities();
        return ResponseEntity.ok(bucketService.convertToDTOList(all));
    }

    @GetMapping("/associations/{associationId}")
    public ResponseEntity<GetBucketMunicipalityDTO> getAssociationById(@PathVariable Long associationId) {
        return bucketService.getBucketMunicipalityById(associationId)
                .map(bucketService::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}/associations")
    public ResponseEntity<List<GetBucketMunicipalityDTO>> getAssociationsByUserAndStatus(
            @PathVariable Long userId,
            @RequestParam Boolean status
    ) {
        List<BucketMunicipality> list = bucketService.getBucketMunicipalitiesByUserIdAndStatus(userId, status);
        return ResponseEntity.ok(bucketService.convertToDTOList(list));
    }

    @PostMapping("/associations")
    public ResponseEntity<Void> createAssociation(@Valid @RequestBody CreateBucketAssociationDTO dto) {
        Bucket bucket = bucketRepository.findById(dto.getBucketId())
                .orElseThrow(() -> new EntityNotFoundException("Bucket not found"));
        Municipality municipality = municipalityRepository.findById(dto.getMunicipalityId())
                .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));

        bucketService.createBucketAssociation(bucket, municipality);
        return ResponseEntity.ok().build();
    }

    // ---------------------- DEPOSIT ----------------------

    @PostMapping("/deposit")
    public ResponseEntity<Void> createDeposit(@Valid @RequestBody CreateDepositDTO dto) {
        Municipality municipality = municipalityRepository.findById(dto.getMunicipalityId())
                .orElseThrow(() -> new EntityNotFoundException("Municipality not found"));
        Container container = containerRepository.findById(dto.getContainerId())
                .orElseThrow(() -> new EntityNotFoundException("Container not found"));

        bucketService.createDeposit(municipality, container, dto.getDepositAmount());
        return ResponseEntity.ok().build();
    }
}
