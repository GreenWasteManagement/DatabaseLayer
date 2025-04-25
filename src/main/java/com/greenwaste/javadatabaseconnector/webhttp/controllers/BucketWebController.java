package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.bucket.request.*;
import com.greenwaste.javadatabaseconnector.dtos.bucket.response.*;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.BucketService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buckets")
public class BucketWebController {

    private final BucketService bucketService;


    public BucketWebController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBucketByIdResponseDTO> getBucketById(@PathVariable Long id) {
        ModelMapper modelMapper = new ModelMapper();

        var bucket = bucketService.getBucketById(id);

        GetBucketByIdResponseDTO responseDTO = modelMapper.map(bucket, GetBucketByIdResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping()
    public ResponseEntity<GetAllBucketsResponseDTO> getAllBuckets() {
        ModelMapper modelMapper = new ModelMapper();

        var buckets = bucketService.getAllBuckets();

        List<GetAllBucketsResponseDTO.Bucket> bucketDTOs = buckets.stream().map(bucket -> modelMapper.map(bucket, GetAllBucketsResponseDTO.Bucket.class)).collect(Collectors.toList());

        GetAllBucketsResponseDTO responseDTO = new GetAllBucketsResponseDTO();
        responseDTO.setBuckets(bucketDTOs);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateBucketResponseDTO> createBucket(@RequestBody CreateBucketRequestDTO requestDTO) {
        ModelMapper modelMapper = new ModelMapper();

        var bucket = modelMapper.map(requestDTO.getBucket(), Bucket.class);

        var createdBucket = bucketService.createBucket(bucket);

        var responseDTO = modelMapper.map(createdBucket, CreateBucketResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }


    @PutMapping("/update")
    public ResponseEntity<UpdateBucketResponseDTO> updateBucket(@RequestBody UpdateBucketRequestDTO requestDTO) {
        ModelMapper modelMapper = new ModelMapper();

        var bucketToUpdate = modelMapper.map(requestDTO.getBucket(), Bucket.class);

        bucketService.updateBucket(bucketToUpdate);

        UpdateBucketResponseDTO responseDTO = new UpdateBucketResponseDTO();
        responseDTO.setMessage("Bucket updated successfully.");

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteBucketResponseDTO> deleteBucket(@RequestBody DeleteBucketRequestDTO requestDTO) {
        ModelMapper modelMapper = new ModelMapper();

        bucketService.deleteBucket(requestDTO.getId());

        DeleteBucketResponseDTO responseDTO = new DeleteBucketResponseDTO();
        responseDTO.setMessage("Bucket deleted successfully");

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/deposit")
    public ResponseEntity<CreateDepositResponseDTO> createDeposit(@RequestBody CreateDepositRequestDTO requestDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Municipality municipality = modelMapper.map(requestDTO.getMunicipality(), Municipality.class);
        Container container = modelMapper.map(requestDTO.getContainer(), Container.class);

        bucketService.createDeposit(municipality, container, requestDTO.getDepositAmount());

        CreateDepositResponseDTO responseDTO = new CreateDepositResponseDTO();
        responseDTO.setDepositTimestamp(Instant.now());

        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/buckets-municipalities")
    public ResponseEntity<GetAllBucketMunicipalitiesResponseDTO> getAll() {
        ModelMapper modelMapper = new ModelMapper();

        var entities = bucketService.getAllBucketMunicipalities();

        List<GetAllBucketMunicipalitiesResponseDTO.BucketMunicipality> bucketMunicipalityDTOs = entities.stream().map(entity -> modelMapper.map(entity, GetAllBucketMunicipalitiesResponseDTO.BucketMunicipality.class)).collect(Collectors.toList());

        GetAllBucketMunicipalitiesResponseDTO responseDTO = new GetAllBucketMunicipalitiesResponseDTO();
        responseDTO.setBucketMunicipalities(bucketMunicipalityDTOs);

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/buckets-municipalities/by-id")
    public ResponseEntity<GetBucketMunicipalityByIdResponseDTO> getById(@RequestBody GetBucketMunicipalityByIdRequestDTO request) {
        ModelMapper modelMapper = new ModelMapper();

        var entity = bucketService.getBucketMunicipalityById(request.getId()).orElseThrow(() -> new EntityNotFoundException("Not found"));

        GetBucketMunicipalityByIdResponseDTO responseDTO = modelMapper.map(entity, GetBucketMunicipalityByIdResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/buckets-municipalities/by-user")
    public ResponseEntity<GetBucketMunicipalitiesByUserIdAndStatusResponseDTO> getByUserAndStatus(@RequestBody GetBucketMunicipalitiesByUserIdAndStatusRequestDTO request) {
        ModelMapper modelMapper = new ModelMapper();

        var list = bucketService.getBucketMunicipalitiesByUserIdAndStatus(request.getUserId(), request.getStatus());

        List<GetBucketMunicipalitiesByUserIdAndStatusResponseDTO.BucketMunicipality> bucketMunicipalities = list.stream().map(entity -> modelMapper.map(entity, GetBucketMunicipalitiesByUserIdAndStatusResponseDTO.BucketMunicipality.class)).collect(Collectors.toList());

        GetBucketMunicipalitiesByUserIdAndStatusResponseDTO responseDTO = new GetBucketMunicipalitiesByUserIdAndStatusResponseDTO();
        responseDTO.setBucketMunicipalities(bucketMunicipalities);

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/buckets-municipalities/by-bucket")
    public ResponseEntity<GetBucketMunicipalitiesByBucketIdAndStatusResponseDTO> getByBucketAndStatus(@RequestBody GetBucketMunicipalitiesByBucketIdAndStatusRequestDTO request) {
        ModelMapper modelMapper = new ModelMapper();

        var list = bucketService.getBucketMunicipalitiesByBucketIdAndStatus(request.getBucketId(), request.getStatus());

        List<GetBucketMunicipalitiesByBucketIdAndStatusResponseDTO.BucketMunicipality> bucketMunicipalities = list.stream().map(entity -> modelMapper.map(entity, GetBucketMunicipalitiesByBucketIdAndStatusResponseDTO.BucketMunicipality.class)).collect(Collectors.toList());

        GetBucketMunicipalitiesByBucketIdAndStatusResponseDTO responseDTO = new GetBucketMunicipalitiesByBucketIdAndStatusResponseDTO();
        responseDTO.setBucketMunicipalities(bucketMunicipalities);

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/buckets-municipalities/first-active-by-user")
    public ResponseEntity<GetFirstBucketMunicipalityByUserAndStatusTrueResponseDTO> getFirstByUserAndTrue(@RequestBody GetFirstBucketMunicipalityByUserAndStatusTrueRequestDTO request) {
        ModelMapper modelMapper = new ModelMapper();

        var municipality = modelMapper.map(request.getMunicipality(), Municipality.class);

        var entity = bucketService.getFirstByUserAndStatusTrue(municipality).orElseThrow(() -> new EntityNotFoundException("No active association"));

        var dto = modelMapper.map(entity, GetFirstBucketMunicipalityByUserAndStatusTrueResponseDTO.class);

        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<Void> createAssociation(@RequestBody CreateBucketAssociationRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        var association = modelMapper.map(dto, CreateBucketAssociationRequestDTO.class);

        bucketService.createBucketAssociation(association.getBucketId(), association.getMunicipalityId());

        return ResponseEntity.ok().build();
    }


    @PostMapping("/bucket-association")
    public ResponseEntity<CreateBucketAssociationResponseDTO> createAndReturn(@RequestBody CreateBucketAssociationRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        BucketMunicipality result = bucketService.createBucketAssociationAndGet(dto.getBucketId(), dto.getMunicipalityId());

        CreateBucketAssociationResponseDTO responseDTO = modelMapper.map(result, CreateBucketAssociationResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/buckets-deposits/count")
    public ResponseEntity<BucketMunicipalityContainerCountResponseDTO> countAll() {
        BucketMunicipalityContainerCountResponseDTO response = bucketService.countAllBucketMunicipalityContainers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("buckets-associations/active")
    public ResponseEntity<List<GetActiveBucketMunicipalityAssociationsResponseDTO>> getActiveAssociations() {
        List<GetActiveBucketMunicipalityAssociationsResponseDTO> result = bucketService.getActiveAssociations();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/deposits/municipality")
    public ResponseEntity<List<GetMunicipalityDepositsResponseDTO>> getDeposits() {

        List<BucketMunicipalityContainer> deposits = bucketService.getAllDepositsForMunicipality();

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        TypeMap<BucketMunicipalityContainer, GetMunicipalityDepositsResponseDTO> typeMap =
                modelMapper.createTypeMap(BucketMunicipalityContainer.class, GetMunicipalityDepositsResponseDTO.class);

        typeMap.setProvider(request -> {
            GetMunicipalityDepositsResponseDTO dto = new GetMunicipalityDepositsResponseDTO();
            dto.setBucket(new GetMunicipalityDepositsResponseDTO.Bucket());
            dto.setMunicipality(new GetMunicipalityDepositsResponseDTO.Municipality());
            dto.setContainer(new GetMunicipalityDepositsResponseDTO.Container());
            return dto;
        });

        typeMap.addMappings(mapper -> {
            mapper.map(BucketMunicipalityContainer::getId, GetMunicipalityDepositsResponseDTO::setDepositId);
            mapper.map(BucketMunicipalityContainer::getDepositAmount, GetMunicipalityDepositsResponseDTO::setDepositAmount);
            mapper.map(BucketMunicipalityContainer::getDepositTimestamp, GetMunicipalityDepositsResponseDTO::setDepositTimestamp);

            // Bucket
            mapper.map(src -> src.getAssociation().getBucket().getId(), (dest, v) -> dest.getBucket().setId((Long) v));
            mapper.map(src -> src.getAssociation().getBucket().getCapacity(), (dest, v) -> dest.getBucket().setCapacity((BigDecimal) v));
            mapper.map(src -> src.getAssociation().getBucket().getIsAssociated(), (dest, v) -> dest.getBucket().setIsAssociated((Boolean) v));

            // Municipality (user)
            mapper.map(src -> src.getAssociation().getUser().getId(), (dest, v) -> dest.getMunicipality().setId((Long) v));
            mapper.map(src -> src.getAssociation().getUser().getId(), (dest, v) -> dest.getMunicipality().setUserId((Long) v));
            mapper.map(src -> src.getAssociation().getUser().getCitizenCardCode(), (dest, v) -> dest.getMunicipality().setCitizenCardCode((String) v));
            mapper.map(src -> src.getAssociation().getUser().getNif(), (dest, v) -> dest.getMunicipality().setNif((String) v));

            // Container
            mapper.map(src -> src.getContainer().getId(), (dest, v) -> dest.getContainer().setId((Long) v));
            mapper.map(src -> src.getContainer().getCapacity(), (dest, v) -> dest.getContainer().setCapacity((BigDecimal) v));
            mapper.map(src -> src.getContainer().getLocalization(), (dest, v) -> dest.getContainer().setLocalization((String) v));
            mapper.map(src -> src.getContainer().getCurrentVolumeLevel(), (dest, v) -> dest.getContainer().setCurrentVolumeLevel((BigDecimal) v));
        });

        List<GetMunicipalityDepositsResponseDTO> response = deposits.stream()
                .map(deposit -> modelMapper.map(deposit, GetMunicipalityDepositsResponseDTO.class))
                .toList();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/with-municipalities")
    public ResponseEntity<List<BucketWithMunicipalityInfoDTO>> getAllBucketsWithMunicipalityInfo() {
        List<Bucket> buckets = bucketService.getAllBucketsWithMunicipalityInfo();

        ModelMapper modelMapper = new ModelMapper();

        List<BucketWithMunicipalityInfoDTO> response = buckets.stream().map(bucket -> {
            BucketWithMunicipalityInfoDTO dto = new BucketWithMunicipalityInfoDTO();
            dto.setBucketId(bucket.getId());
            dto.setCapacity(bucket.getCapacity());

            List<BucketWithMunicipalityInfoDTO.BucketMunicipalityDTO> bmDTOs = bucket.getBucketMunicipalities().stream().map(bm -> {
                BucketWithMunicipalityInfoDTO.BucketMunicipalityDTO bmDTO = new BucketWithMunicipalityInfoDTO.BucketMunicipalityDTO();
                bmDTO.setId(bm.getId());
                bmDTO.setStatus(bm.getStatus());

                BucketWithMunicipalityInfoDTO.MunicipalityDTO mDTO = new BucketWithMunicipalityInfoDTO.MunicipalityDTO();
                mDTO.setId(bm.getUser().getId());
                mDTO.setNif(bm.getUser().getNif());
                mDTO.setCitizenCardCode(bm.getUser().getCitizenCardCode());

                User user = bm.getUser().getUser();
                if (user != null) {
                    BucketWithMunicipalityInfoDTO.UserDTO userDTO = new BucketWithMunicipalityInfoDTO.UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setName(user.getName());
                    userDTO.setEmail(user.getEmail());
                    mDTO.setUser(userDTO);
                }

                bmDTO.setMunicipality(mDTO);
                return bmDTO;
            }).collect(Collectors.toList());

            dto.setBucketMunicipalities(bmDTOs);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
