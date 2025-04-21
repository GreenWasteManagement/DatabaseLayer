package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.bucket.request.*;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.*;
import com.greenwaste.javadatabaseconnector.mapper.BucketDTOMapper;
import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import com.greenwaste.javadatabaseconnector.service.BucketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/buckets")
public class BucketWebController {

    private final BucketService bucketService;
    private final BucketDTOMapper bucketDTOMapper;

    public BucketWebController(BucketService bucketService, BucketDTOMapper bucketDTOMapper) {
        this.bucketService = bucketService;
        this.bucketDTOMapper = bucketDTOMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBucketByIdResponseDTO> getBucketById(@RequestBody GetBucketByIdRequestDTO requestDTO) {
        var bucket = bucketService.getBucketById(requestDTO.getId());
        var responseDTO = bucketDTOMapper.toGetBucketByIdResponseDTO(bucket);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping()
    public ResponseEntity<GetAllBucketsResponseDTO> getAllBuckets() {
        var buckets = bucketService.getAllBuckets();
        var responseDTO = bucketDTOMapper.toGetAllBucketsResponseDTO(buckets);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateBucketResponseDTO> createBucket(@RequestBody CreateBucketRequestDTO requestDTO) {
        var bucket = bucketService.createBucket(bucketDTOMapper.toBucket(requestDTO));
        var responseDTO = bucketDTOMapper.toCreateBucketResponseDTO(bucket);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateBucketResponseDTO> updateBucket(@RequestBody UpdateBucketRequestDTO requestDTO) {
        bucketService.updateBucket(bucketDTOMapper.toBucket(requestDTO));
        return ResponseEntity.ok(new UpdateBucketResponseDTO("Bucket updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteBucketResponseDTO> deleteBucket(@RequestBody DeleteBucketRequestDTO requestDTO) {
        bucketService.deleteBucket(requestDTO.getId());
        return ResponseEntity.ok(new DeleteBucketResponseDTO("Bucket deleted successfully"));
    }

    @PostMapping("/deposit")
    public ResponseEntity<CreateDepositResponseDTO> createDeposit(@RequestBody CreateDepositRequestDTO requestDTO) {
        var municipality = bucketDTOMapper.toMunicipality(requestDTO.getMunicipality());
        var container = bucketDTOMapper.toContainer(requestDTO.getContainer());

        bucketService.createDeposit(municipality, container, requestDTO.getDepositAmount());

        return ResponseEntity.ok(new CreateDepositResponseDTO(Instant.now()));
    }

    @GetMapping("/buckets-municipalities")
    public ResponseEntity<GetAllBucketMunicipalitiesResponseDTO> getAll() {
        var entities = bucketService.getAllBucketMunicipalities();
        var dto = new GetAllBucketMunicipalitiesResponseDTO(bucketDTOMapper.toDTOList(entities));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/buckets-municipalities/by-id")
    public ResponseEntity<GetBucketMunicipalityByIdResponseDTO> getById(@RequestBody GetBucketMunicipalityByIdRequestDTO request) {
        var entity = bucketService.getBucketMunicipalityById(request.getId()).orElseThrow(() -> new EntityNotFoundException("Not found"));
        var dto = new GetBucketMunicipalityByIdResponseDTO(bucketDTOMapper.toDTO(entity));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/buckets-municipalities/by-user")
    public ResponseEntity<GetBucketMunicipalitiesByUserIdAndStatusResponseDTO> getByUserAndStatus(@RequestBody GetBucketMunicipalitiesByUserIdAndStatusRequestDTO request) {
        var list = bucketService.getBucketMunicipalitiesByUserIdAndStatus(request.getUserId(), request.getStatus());
        var dto = new GetBucketMunicipalitiesByUserIdAndStatusResponseDTO(bucketDTOMapper.toDTOList(list));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/buckets-municipalities/by-bucket")
    public ResponseEntity<GetBucketMunicipalitiesByBucketIdAndStatusResponseDTO> getByBucketAndStatus(@RequestBody GetBucketMunicipalitiesByBucketIdAndStatusRequestDTO request) {
        var list = bucketService.getBucketMunicipalitiesByBucketIdAndStatus(request.getBucketId(), request.getStatus());
        var dto = new GetBucketMunicipalitiesByBucketIdAndStatusResponseDTO(bucketDTOMapper.toDTOList(list));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/buckets-municipalities/first-active-by-user")
    public ResponseEntity<GetFirstBucketMunicipalityByUserAndStatusTrueResponseDTO> getFirstByUserAndTrue(@RequestBody GetFirstBucketMunicipalityByUserAndStatusTrueRequestDTO request) {
        var municipality = bucketDTOMapper.toEntity(request.getMunicipality());
        var entity = bucketService.getFirstByUserAndStatusTrue(municipality).orElseThrow(() -> new EntityNotFoundException("No active association"));
        var dto = new GetFirstBucketMunicipalityByUserAndStatusTrueResponseDTO(bucketDTOMapper.toDTO(entity));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> createAssociation(@RequestBody CreateBucketAssociationRequestDTO dto) {
        bucketService.createBucketAssociation(dto.getBucketId(), dto.getMunicipalityId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/with-response")
    public ResponseEntity<CreateBucketAssociationResponseDTO> createAndReturn(@RequestBody CreateBucketAssociationRequestDTO dto) {
        BucketMunicipality result = bucketService.createBucketAssociationAndGet(dto.getBucketId(), dto.getMunicipalityId());
        return ResponseEntity.ok(bucketDTOMapper.toCreateBucketAssociationResponseDTO(result));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> createDeposit(@RequestBody CreateDepositByIdsRequestDTO dto) {
        bucketService.createDepositByIds(dto.getMunicipalityId(), dto.getContainerId(), dto.getDepositAmount());
        return ResponseEntity.ok().build();
    }

}
