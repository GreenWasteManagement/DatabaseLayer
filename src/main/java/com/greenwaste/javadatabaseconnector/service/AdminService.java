package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.Admin;
import com.greenwaste.javadatabaseconnector.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));
    }

    @Transactional
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Transactional
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Admin existing = getAdminById(id);
        existing.setCitizenCardCode(updatedAdmin.getCitizenCardCode());
        existing.setUser(updatedAdmin.getUser());
        return adminRepository.save(existing);
    }

    @Transactional
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
