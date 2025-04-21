package com.greenwaste.javadatabaseconnector.webhttp.controllers;

import com.greenwaste.javadatabaseconnector.dtos.base.AdminDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.MunicipalityDTO;
import com.greenwaste.javadatabaseconnector.dtos.base.SmasDTO;
import com.greenwaste.javadatabaseconnector.mapper.UserDTOMapper;
import com.greenwaste.javadatabaseconnector.model.Admin;
import com.greenwaste.javadatabaseconnector.model.Municipality;
import com.greenwaste.javadatabaseconnector.model.Smas;
import com.greenwaste.javadatabaseconnector.model.User;
import com.greenwaste.javadatabaseconnector.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserWebController {

    private final UserService userService;
    private final UserDTOMapper mapper;

    // Admin
    @PostMapping("/admin")
    public ResponseEntity<Map<String, Object>> createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        User user = new User();  // Criar a instância de User (deve ser validado na camada de serviço)
        Admin admin = new Admin();
        // Associa os dados recebidos aos objetos
        admin.setCitizenCardCode(adminDTO.getCitizenCardCode());  // Exemplo de mapeamento
        // Aqui você vai criar o usuário junto com o admin, passando os dados da DTO
        admin = userService.createAdmin(user, admin, adminDTO.getAddress(), adminDTO.getPostalCode());
        return ResponseEntity.ok(mapper.mapToAdminList(List.of(admin)));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Map<String, Object>> getAdminById(@PathVariable Long id) {
        Admin admin = userService.getAdminById(id);
        return ResponseEntity.ok(mapper.mapToAdminList(List.of(admin)));
    }

    @GetMapping("/admin")
    public ResponseEntity<Map<String, Object>> listAdmins() {
        List<Admin> all = userService.getAllAdmins();
        return ResponseEntity.ok(mapper.mapToAdminList(all));
    }

    // Smas
    @PostMapping("/smas")
    public ResponseEntity<Map<String, Object>> createSmas(@Valid @RequestBody SmasDTO dto) {
        User user = new User();
        Smas smas = new Smas();
        smas.setCitizenCardCode(dto.getCitizenCardCode());
        smas.setEmployeeCode(dto.getEmployeeCode());
        smas.setPosition(dto.getPosition());
        smas = userService.createSmas(user, smas, dto.getAddress(), dto.getPostalCode());
        return ResponseEntity.ok(mapper.mapToSmasList(List.of(smas)));
    }

    @GetMapping("/smas/{id}")
    public ResponseEntity<Map<String, Object>> getSmasById(@PathVariable Long id) {
        Smas smas = userService.getSmasById(id);
        return ResponseEntity.ok(mapper.mapToSmasList(List.of(smas)));
    }

    @GetMapping("/smas")
    public ResponseEntity<Map<String, Object>> listSmas() {
        List<Smas> all = userService.getAllSmas();
        return ResponseEntity.ok(mapper.mapToSmasList(all));
    }

    // Municipalities
    @PostMapping("/municipality")
    public ResponseEntity<Map<String, Object>> createMunicipality(@Valid @RequestBody MunicipalityDTO dto) {
        User user = new User();
        Municipality municipality = new Municipality();
        municipality.setCitizenCardCode(dto.getCitizenCardCode());
        municipality.setNif(dto.getNif());
        municipality = userService.createMunicipality(user, municipality, dto.getAddress(), dto.getPostalCode());
        return ResponseEntity.ok(mapper.mapToMunicipalityList(List.of(municipality)));
    }

    @GetMapping("/municipality/{id}")
    public ResponseEntity<Map<String, Object>> getMunicipalityById(@PathVariable Long id) {
        Municipality municipality = userService.getMunicipalityById(id);
        return ResponseEntity.ok(mapper.mapToMunicipalityList(List.of(municipality)));
    }

    @GetMapping("/municipality")
    public ResponseEntity<Map<String, Object>> listMunicipalities() {
        List<Municipality> list = userService.getAllMunicipalities();
        return ResponseEntity.ok(mapper.mapToMunicipalityList(list));
    }

    // Atualização de Admin
    @PutMapping("/admin/{id}")
    public ResponseEntity<Map<String, Object>> updateAdmin(@PathVariable Long id, @Valid @RequestBody AdminDTO dto) {
        Admin updatedAdmin = new Admin();
        updatedAdmin.setId(id);
        updatedAdmin.setCitizenCardCode(dto.getCitizenCardCode());
        userService.updateAdmin(updatedAdmin);
        return ResponseEntity.ok(mapper.mapToAdminList(List.of(updatedAdmin)));
    }

    // Atualização de Smas
    @PutMapping("/smas/{id}")
    public ResponseEntity<Map<String, Object>> updateSmas(@PathVariable Long id, @Valid @RequestBody SmasDTO dto) {
        Smas updatedSmas = new Smas();
        updatedSmas.setId(id);
        updatedSmas.setCitizenCardCode(dto.getCitizenCardCode());
        updatedSmas.setEmployeeCode(dto.getEmployeeCode());
        updatedSmas.setPosition(dto.getPosition());
        userService.updateSmas(updatedSmas);
        return ResponseEntity.ok(mapper.mapToSmasList(List.of(updatedSmas)));
    }

    // Atualização de Municipality
    @PutMapping("/municipality/{id}")
    public ResponseEntity<Map<String, Object>> updateMunicipality(@PathVariable Long id, @Valid @RequestBody MunicipalityDTO dto) {
        Municipality updatedMunicipality = new Municipality();
        updatedMunicipality.setId(id);
        updatedMunicipality.setCitizenCardCode(dto.getCitizenCardCode());
        updatedMunicipality.setNif(dto.getNif());
        userService.updateMunicipality(updatedMunicipality);
        return ResponseEntity.ok(mapper.mapToMunicipalityList(List.of(updatedMunicipality)));
    }
}
