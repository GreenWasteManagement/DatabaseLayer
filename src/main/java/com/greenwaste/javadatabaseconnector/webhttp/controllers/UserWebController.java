package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.user.request.*;
import com.greenwaste.javadatabaseconnector.dtos.user.response.*;
import com.greenwaste.javadatabaseconnector.model.*;
import com.greenwaste.javadatabaseconnector.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        user.setRole(User.user_role.valueOf("ADMIN"));

        System.out.println(postalCode.getCounty());

        userService.createAdmin(user, admin, address, postalCode);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/create/municipality")
    public ResponseEntity<CreateMunicipalityResponseDTO> createMunicipality(@RequestBody CreateMunicipalityRequestDTO dto) {
        ModelMapper modelMapper = new ModelMapper();


        var user = modelMapper.map(dto.getUser(), User.class);
        var municipality = modelMapper.map(dto.getMunicipality(), Municipality.class);
        var address = modelMapper.map(dto.getAddress(), Address.class);
        var postalCode = modelMapper.map(dto.getPostalCode(), PostalCode.class);

        user.setRole(User.user_role.valueOf("MUNICIPALITY"));

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

        user.setRole(User.user_role.valueOf("SMAS"));

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


    @PostMapping("/get/admin/{id}")
    public ResponseEntity<GetAdminByIdResponseDTO> getAdminById(@PathVariable Long id) {

        ModelMapper modelMapper = new ModelMapper();

        User user = userService.getAdminById(id);

        GetAdminByIdResponseDTO dto = new GetAdminByIdResponseDTO();

        // Map internal classes
        dto.setUser(modelMapper.map(user, GetAdminByIdResponseDTO.User.class));
        dto.setAdmin(modelMapper.map(user.getAdmin(), GetAdminByIdResponseDTO.Admin.class));
        dto.setAddress(modelMapper.map(user.getAddress(), GetAdminByIdResponseDTO.Address.class));
        dto.setPostalCode(modelMapper.map(user.getAddress().getPostalCode(), GetAdminByIdResponseDTO.PostalCode.class));

        return ResponseEntity.ok(dto);
    }


    @PostMapping("/get/smas/{id}")
    public ResponseEntity<GetSmasByIdResponseDTO> getSmasById(@PathVariable Long id) {

        ModelMapper modelMapper = new ModelMapper();

        Smas smas = userService.getSmasById(id).getSmas();
        User user = smas.getUser();

        GetSmasByIdResponseDTO responseDTO = new GetSmasByIdResponseDTO();

        responseDTO.setUser(modelMapper.map(user, GetSmasByIdResponseDTO.User.class));

        responseDTO.setSmas(modelMapper.map(smas, GetSmasByIdResponseDTO.Smas.class));

        responseDTO.setAddress(modelMapper.map(user.getAddress(), GetSmasByIdResponseDTO.Address.class));
        responseDTO.setPostalCode(modelMapper.map(user.getAddress().getPostalCode(), GetSmasByIdResponseDTO.PostalCode.class));

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping("/get/municipality/{id}")
    public ResponseEntity<GetMunicipalityByIdResponseDTO> getMunicipalityById(@PathVariable Long id) {

        ModelMapper modelMapper = new ModelMapper();

        Municipality municipality = userService.getMunicipalityById(id).getMunicipality();

        User user = municipality.getUser();

        GetMunicipalityByIdResponseDTO responseDTO = new GetMunicipalityByIdResponseDTO();

        responseDTO.setUser(modelMapper.map(user, GetMunicipalityByIdResponseDTO.User.class));

        responseDTO.setMunicipality(modelMapper.map(municipality, GetMunicipalityByIdResponseDTO.Municipality.class));

        responseDTO.setAddress(modelMapper.map(user.getAddress(), GetMunicipalityByIdResponseDTO.Address.class));
        responseDTO.setPostalCode(modelMapper.map(user.getAddress().getPostalCode(), GetMunicipalityByIdResponseDTO.PostalCode.class));

        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/get/admins")
    public ResponseEntity<GetAllAdminsResponseDTO> getAllAdmins() {

        ModelMapper modelMapper = new ModelMapper();

        List<User> users = userService.getAllAdmins();

        List<GetAllAdminsResponseDTO.AdminData> adminDataList = users.stream().map(user -> {
            GetAllAdminsResponseDTO.AdminData adminData = new GetAllAdminsResponseDTO.AdminData();

            adminData.setUser(modelMapper.map(user, GetAllAdminsResponseDTO.User.class));
            adminData.setAdmin(modelMapper.map(user.getAdmin(), GetAllAdminsResponseDTO.Admin.class));
            adminData.setAddress(modelMapper.map(user.getAddress(), GetAllAdminsResponseDTO.Address.class));
            adminData.setPostalCode(modelMapper.map(user.getAddress().getPostalCode(), GetAllAdminsResponseDTO.PostalCode.class));

            return adminData;
        }).collect(Collectors.toList());

        GetAllAdminsResponseDTO responseDTO = new GetAllAdminsResponseDTO();
        responseDTO.setAdmins(adminDataList);

        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/get/smas")
    public ResponseEntity<GetAllSmasResponseDTO> getAllSmas() {

        ModelMapper modelMapper = new ModelMapper();


        List<User> users = userService.getAllSmas();

        List<GetAllSmasResponseDTO.SmasData> smasDataList = users.stream().map(user -> {
            GetAllSmasResponseDTO.SmasData smasData = new GetAllSmasResponseDTO.SmasData();

            smasData.setUser(modelMapper.map(user, GetAllSmasResponseDTO.User.class));
            smasData.setSmas(modelMapper.map(user.getSmas(), GetAllSmasResponseDTO.Smas.class));
            smasData.setAddress(modelMapper.map(user.getAddress(), GetAllSmasResponseDTO.Address.class));
            smasData.setPostalCode(modelMapper.map(user.getAddress().getPostalCode(), GetAllSmasResponseDTO.PostalCode.class));

            return smasData;
        }).collect(Collectors.toList());

        GetAllSmasResponseDTO responseDTO = new GetAllSmasResponseDTO();
        responseDTO.setSmasList(smasDataList);

        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/get/municipalities")
    public ResponseEntity<GetAllMunicipalitiesResponseDTO> getAllMunicipalities() {
        ModelMapper modelMapper = new ModelMapper();

        List<User> users = userService.getAllMunicipalities();

        List<GetAllMunicipalitiesResponseDTO.MunicipalityData> municipalityDataList = users.stream().map(user -> {
            GetAllMunicipalitiesResponseDTO.MunicipalityData municipalityData = new GetAllMunicipalitiesResponseDTO.MunicipalityData();

            municipalityData.setUser(modelMapper.map(user, GetAllMunicipalitiesResponseDTO.User.class));
            municipalityData.setMunicipality(modelMapper.map(user.getMunicipality(), GetAllMunicipalitiesResponseDTO.Municipality.class));
            municipalityData.setAddress(modelMapper.map(user.getAddress(), GetAllMunicipalitiesResponseDTO.Address.class));
            municipalityData.setPostalCode(modelMapper.map(user.getAddress().getPostalCode(), GetAllMunicipalitiesResponseDTO.PostalCode.class));

            return municipalityData;
        }).collect(Collectors.toList());

        GetAllMunicipalitiesResponseDTO responseDTO = new GetAllMunicipalitiesResponseDTO();
        responseDTO.setMunicipalities(municipalityDataList);

        return ResponseEntity.ok(responseDTO);
    }


}
