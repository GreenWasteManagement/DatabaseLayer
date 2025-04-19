package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Municipality;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {

    @EntityGraph(attributePaths = {
            "user",
            "user.address",
            "user.address.postalCode"
    })
    Optional<Municipality> findWithAllDetailsById(Long id);


}
