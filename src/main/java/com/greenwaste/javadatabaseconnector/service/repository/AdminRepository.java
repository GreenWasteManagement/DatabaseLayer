package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.Admin;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @EntityGraph(attributePaths = {
            "user",
            "user.address",
            "user.address.postalCode"
    })
    Optional<Admin> findWithAllDetailsById(Long id);

    @EntityGraph(attributePaths = {
            "user",
            "user.address",
            "user.address.postalCode"
    })
    List<Admin> findAll();


}
