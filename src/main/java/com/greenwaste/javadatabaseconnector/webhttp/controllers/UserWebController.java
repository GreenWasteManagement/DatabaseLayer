package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.base.AdminDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.request.*;
import com.greenwaste.javadatabaseconnector.dtos.user.response.*;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserWebController {


    private final UserService userService;



    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        ModelMapper modelMapper = new ModelMapper();


        User user = modelMapper.map(request, User.class);

        String token = userService.login(user.getEmail(), user.getPassword());

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setToken(token);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/create/admin")
    public ResponseEntity<CreateAdminResponseDTO> createAdmin(@RequestBody CreateAdminRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        User user = modelMapper.map(dto.getUser(), User.class);
        Admin admin = modelMapper.map(dto.getAdmin(), Admin.class);
        Address address = modelMapper.map(dto.getAddress(), Address.class);
        PostalCode postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);


        System.out.println(postalCode.getCounty());

        userService.createAdmin(user, admin, address, postalCode);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/municipality")
    public ResponseEntity<CreateMunicipalityResponseDTO> createMunicipality(@RequestBody CreateMunicipalityRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        System.out.println(dto.getPostalCode().getCounty());

        var user = modelMapper.map(dto.getUser(), User.class);
        var municipality = modelMapper.map(dto.getMunicipality(), Municipality.class);
        var address = modelMapper.map(dto.getAddress(), Address.class);
        var postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);

        System.out.println(user.getEmail());
        System.out.println(postalCode.getPostalCode());
        System.out.println(postalCode.getCounty());

        var savedMunicipality = userService.createMunicipality(user, municipality, address, postalCode);

        var responseDTO = new CreateMunicipalityResponseDTO();
        responseDTO.setMunicipality(modelMapper.map(savedMunicipality, CreateMunicipalityResponseDTO.Municipality.class));

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/create/smas")
    public ResponseEntity<CreateSmasResponseDTO> createSmas(@RequestBody CreateSmasRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();


        var user = modelMapper.map(dto.getUser(), User.class);
        var smas = modelMapper.map(dto.getSmas(), Smas.class);
        var address = modelMapper.map(dto.getAddress(), Address.class);
        var postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);

        var savedSmas = userService.createSmas(user, smas, address, postalCode);

        var responseDTO = new CreateSmasResponseDTO();
        responseDTO.setSmas(modelMapper.map(savedSmas, CreateSmasResponseDTO.Smas.class));

        return ResponseEntity.ok(responseDTO);
    }


    @PutMapping("/update")
    public ResponseEntity<UpdateSuccessResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        User user = modelMapper.map(dto.getUser(), User.class);

        userService.updateUser(user);

        UpdateSuccessResponseDTO responseDTO = new UpdateSuccessResponseDTO();
        responseDTO.setMessage("User updated successfully.");

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/address")
    public ResponseEntity<UpdateSuccessResponseDTO> updateAddress(@RequestBody UpdateAddressRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        Address address = modelMapper.map(dto.getAddress(), Address.class);

        userService.updateUserAddress(address);

        UpdateSuccessResponseDTO responseDTO = new UpdateSuccessResponseDTO();
        responseDTO.setMessage("Address updated successfully.");

        return ResponseEntity.ok(responseDTO);
    }


    @PutMapping("/update/admin")
    public ResponseEntity<UpdateSuccessResponseDTO> updateAdmin(@RequestBody UpdateAdminRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        Admin admin = modelMapper.map(dto.getAdmin(), Admin.class);

        userService.updateAdmin(admin);

        UpdateSuccessResponseDTO responseDTO = new UpdateSuccessResponseDTO();
        responseDTO.setMessage("Admin updated successfully.");

        return ResponseEntity.ok(responseDTO);
    }


    @PutMapping("/update/smas")
    public ResponseEntity<UpdateSuccessResponseDTO> updateSmas(@RequestBody UpdateSmasRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        Smas smas = modelMapper.map(dto.getSmas(), Smas.class);

        userService.updateSmas(smas);

        UpdateSuccessResponseDTO responseDTO = new UpdateSuccessResponseDTO();
        responseDTO.setMessage("SMAS updated successfully.");

        return ResponseEntity.ok(responseDTO);
    }


    @PutMapping("/update/municipality")
    public ResponseEntity<UpdateSuccessResponseDTO> updateMunicipality(@RequestBody UpdateMunicipalityRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        Municipality municipality = modelMapper.map(dto.getMunicipality(), Municipality.class);

        userService.updateMunicipality(municipality);

        UpdateSuccessResponseDTO responseDTO = new UpdateSuccessResponseDTO();
        responseDTO.setMessage("Municipality updated successfully.");

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/postalcode")
    public ResponseEntity<UpdateSuccessResponseDTO> updatePostalCode(@RequestBody UpdatePostalCodeRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        PostalCode postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);

        userService.updatePostalCode(postalCode);

        UpdateSuccessResponseDTO responseDTO = new UpdateSuccessResponseDTO();
        responseDTO.setMessage("Postal code updated successfully.");

        return ResponseEntity.ok(responseDTO);
    }


    @DeleteMapping("/delete/user")
    public ResponseEntity<UpdateSuccessResponseDTO> deleteUser(@RequestBody DeleteUserRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        User user = modelMapper.map(dto.getUser(), User.class);

        userService.deleteUser(user);

        UpdateSuccessResponseDTO responseDTO = new UpdateSuccessResponseDTO();
        responseDTO.setMessage("User deleted successfully.");

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/postalcode")
    public ResponseEntity<UpdateSuccessResponseDTO> deletePostalCode(@RequestBody DeletePostalCodeRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        PostalCode postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);

        userService.deletePostalCode(postalCode);

        UpdateSuccessResponseDTO responseDTO = new UpdateSuccessResponseDTO();
        responseDTO.setMessage("Postal code deleted successfully.");

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/get/admin")
    public ResponseEntity<AdminDTO> getAdminById(@RequestBody GetAdminByIdRequestDTO dto) {
        Admin admin = userService.getAdminById(dto.getId());

        ModelMapper modelMapper = new ModelMapper();
        AdminDTO responseDTO = modelMapper.map(admin, AdminDTO.class);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/get/smas")
    public ResponseEntity<SmasDTO> getSmasById(@RequestBody GetSmasByIdRequestDTO dto) {
        Smas smas = userService.getSmasById(dto.getId());

        ModelMapper modelMapper = new ModelMapper();
        SmasDTO responseDTO = modelMapper.map(smas, SmasDTO.class);

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/get/municipality")
    public ResponseEntity<MunicipalityDTO> getMunicipalityById(@RequestBody GetMunicipalityByIdRequestDTO dto) {
        Municipality municipality = userService.getMunicipalityById(dto.getId());

        ModelMapper modelMapper = new ModelMapper();
        MunicipalityDTO responseDTO = modelMapper.map(municipality, MunicipalityDTO.class);

        Set<Long> bucketMunicipalityIds = municipality.getBucketMunicipalities() == null ? Collections.emptySet() : municipality.getBucketMunicipalities().stream().map(BucketMunicipality::getId).collect(Collectors.toSet());

        responseDTO.setBucketMunicipalityIds(bucketMunicipalityIds);

        return ResponseEntity.ok(responseDTO);
    }

}
