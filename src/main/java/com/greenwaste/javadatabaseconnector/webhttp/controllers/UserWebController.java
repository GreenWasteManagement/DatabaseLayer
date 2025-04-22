package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.base.AdminDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.request.*;
import com.greenwaste.javadatabaseconnector.dtos.user.response.CreateAdminResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.response.CreateMunicipalityResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.response.CreateSmasResponseDTO;
import com.greenwaste.javadatabaseconnector.dtos.user.response.UpdateSuccessResponseDTO;
import com.greenwaste.javadatabaseconnector.mapper.UserDTOMapper;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserWebController {

    private final UserService userService;
    private final UserDTOMapper userDTOMapper;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/create/admin")
    public ResponseEntity<CreateAdminResponseDTO> createAdmin(@RequestBody CreateAdminRequestDTO dto) {
        var user = userDTOMapper.toUser(dto.getUser());
        var admin = userDTOMapper.toAdmin(dto.getAdmin());
        var address = userDTOMapper.toAddress(dto.getAddress());
        var postalCode = userDTOMapper.toPostalCode(dto.getPostalCode());

        var savedAdmin = userService.createAdmin(user, admin, address, postalCode);
        var responseDTO = new CreateAdminResponseDTO(userDTOMapper.toAdminDTO(savedAdmin));
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/create/municipality")
    public ResponseEntity<CreateMunicipalityResponseDTO> createMunicipality(@RequestBody CreateMunicipalityRequestDTO dto) {
        var user = userDTOMapper.toUser(dto.getUser());
        var municipality = userDTOMapper.toMunicipality(dto.getMunicipality());
        var address = userDTOMapper.toAddress(dto.getAddress());
        var postalCode = userDTOMapper.toPostalCode(dto.getPostalCode());


        System.out.println(user);
        System.out.println(municipality);
        System.out.println(address);
        System.out.println(postalCode.getCounty());

        var savedMunicipality = userService.createMunicipality(user, municipality, address, postalCode);
        var responseDTO = new CreateMunicipalityResponseDTO(userDTOMapper.toMunicipalityDTO(savedMunicipality));
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/create/smas")
    public ResponseEntity<CreateSmasResponseDTO> createSmas(@RequestBody CreateSmasRequestDTO dto) {
        var user = userDTOMapper.toUser(dto.getUser());
        var smas = userDTOMapper.toSmas(dto.getSmas());
        var address = userDTOMapper.toAddress(dto.getAddress());
        var postalCode = userDTOMapper.toPostalCode(dto.getPostalCode());

        var savedSmas = userService.createSmas(user, smas, address, postalCode);
        var responseDTO = new CreateSmasResponseDTO(userDTOMapper.toSmasDTO(savedSmas));
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateSuccessResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO dto) {
        User user = userDTOMapper.toUser(dto.getUser());
        userService.updateUser(user);
        return ResponseEntity.ok(new UpdateSuccessResponseDTO("User updated successfully."));
    }

    @PutMapping("/update/address")
    public ResponseEntity<UpdateSuccessResponseDTO> updateAddress(@RequestBody UpdateAddressRequestDTO dto) {
        Address address = userDTOMapper.toAddress(dto.getAddress());
        userService.updateUserAddress(address);
        return ResponseEntity.ok(new UpdateSuccessResponseDTO("Address updated successfully."));
    }

    @PutMapping("/update/admin")
    public ResponseEntity<UpdateSuccessResponseDTO> updateAdmin(@RequestBody UpdateAdminRequestDTO dto) {
        Admin admin = userDTOMapper.toAdmin(dto.getAdmin());
        userService.updateAdmin(admin);
        return ResponseEntity.ok(new UpdateSuccessResponseDTO("Admin updated successfully."));
    }


    @PutMapping("/update/smas")
    public ResponseEntity<UpdateSuccessResponseDTO> updateSmas(@RequestBody UpdateSmasRequestDTO dto) {
        Smas smas = userDTOMapper.toSmas(dto.getSmas());
        userService.updateSmas(smas);
        return ResponseEntity.ok(new UpdateSuccessResponseDTO("SMAS updated successfully."));
    }

    @PutMapping("/update/municipality")
    public ResponseEntity<UpdateSuccessResponseDTO> updateMunicipality(@RequestBody UpdateMunicipalityRequestDTO dto) {
        Municipality municipality = userDTOMapper.toMunicipality(dto.getMunicipality());
        userService.updateMunicipality(municipality);
        return ResponseEntity.ok(new UpdateSuccessResponseDTO("Municipality updated successfully."));
    }

    @PutMapping("/update/postalcode")
    public ResponseEntity<UpdateSuccessResponseDTO> updatePostalCode(@RequestBody UpdatePostalCodeRequestDTO dto) {
        PostalCode postalCode = userDTOMapper.toPostalCode(dto.getPostalCode());
        userService.updatePostalCode(postalCode);
        return ResponseEntity.ok(new UpdateSuccessResponseDTO("Postal code updated successfully."));
    }


    @DeleteMapping("/delete/user")
    public ResponseEntity<UpdateSuccessResponseDTO> deleteUser(@RequestBody DeleteUserRequestDTO dto) {
        User user = userDTOMapper.toUser(dto.getUser());
        userService.deleteUser(user);
        return ResponseEntity.ok(new UpdateSuccessResponseDTO("User deleted successfully."));
    }

    @DeleteMapping("/delete/postalcode")
    public ResponseEntity<UpdateSuccessResponseDTO> deletePostalCode(@RequestBody DeletePostalCodeRequestDTO dto) {
        PostalCode postalCode = userDTOMapper.toPostalCode(dto.getPostalCode());
        userService.deletePostalCode(postalCode);
        return ResponseEntity.ok(new UpdateSuccessResponseDTO("Postal code deleted successfully."));
    }

    @PostMapping("/get/admin")
    public ResponseEntity<AdminDTO> getAdminById(@RequestBody GetAdminByIdRequestDTO dto) {
        Admin admin = userService.getAdminById(dto.getId());
        return ResponseEntity.ok(userDTOMapper.toAdminDTO(admin));
    }

    @PostMapping("/get/smas")
    public ResponseEntity<SmasDTO> getSmasById(@RequestBody GetSmasByIdRequestDTO dto) {
        Smas smas = userService.getSmasById(dto.getId());
        return ResponseEntity.ok(userDTOMapper.toSmasDTO(smas));
    }

    @PostMapping("/get/municipality")
    public ResponseEntity<MunicipalityDTO> getMunicipalityById(@RequestBody GetMunicipalityByIdRequestDTO dto) {
        Municipality municipality = userService.getMunicipalityById(dto.getId());
        return ResponseEntity.ok(userDTOMapper.toMunicipalityDTO(municipality));
    }

}
