package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.Smas;
import com.greenwaste.javadatabaseconnector.repository.SmasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmasService {

    private final SmasRepository smasRepository;

    public SmasService(SmasRepository smasRepository) {
        this.smasRepository = smasRepository;
    }

    public List<Smas> getAllSMAS() {
        return smasRepository.findAll();
    }

    public Smas getSMASById(Long id) {
        return smasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Smas not found"));
    }

    @Transactional
    public Smas createSMAS(Smas smas) {
        return smasRepository.save(smas);
    }

    @Transactional
    public Smas updateSMAS(Long id, Smas updatedSMAS) {
        Smas existing = getSMASById(id);
        existing.setPosition(updatedSMAS.getPosition());
        existing.setEmployeeCode(updatedSMAS.getEmployeeCode());
        existing.setCitizenCardCode(updatedSMAS.getCitizenCardCode());
        existing.setUser(updatedSMAS.getUser());
        return smasRepository.save(existing);
    }

    @Transactional
    public void deleteSMAS(Long id) {
        smasRepository.deleteById(id);
    }
}
