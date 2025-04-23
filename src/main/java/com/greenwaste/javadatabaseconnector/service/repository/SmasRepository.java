package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.Admin;
import com.greenwaste.javadatabaseconnector.model.Smas;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SmasRepository extends JpaRepository<Smas, Long> {

    @EntityGraph(attributePaths = {
            "user",
            "user.address",
            "user.address.postalCode"
    })
    Optional<Smas> findWithAllDetailsById(Long id);

    @EntityGraph(attributePaths = {
            "user",
            "user.address",
            "user.address.postalCode"
    })
    List<Smas> findAll();


}