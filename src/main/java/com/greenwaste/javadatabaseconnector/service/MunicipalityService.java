package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.Municipality;
import com.greenwaste.javadatabaseconnector.repository.MunicipalityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MunicipalityService {

    private final MunicipalityRepository municipalityRepository;

    public MunicipalityService(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    public List<Municipality> getAllMunicipalities() {
        return municipalityRepository.findAll();
    }

    public Municipality getMunicipalityById(Long id) {
        return municipalityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Municipality not found"));
    }

    @Transactional
    public Municipality createMunicipality(Municipality municipality) {
        return municipalityRepository.save(municipality);
    }

    @Transactional
    public Municipality updateMunicipality(Long id, Municipality updatedMunicipality) {
        Municipality existing = getMunicipalityById(id);
        existing.setCitizenCardCode(updatedMunicipality.getCitizenCardCode());
        existing.setNif(updatedMunicipality.getNif());
        existing.setUser(updatedMunicipality.getUser());
        return municipalityRepository.save(existing);
    }

    @Transactional
    public void deleteMunicipality(Long id) {
        municipalityRepository.deleteById(id);
    }
}
