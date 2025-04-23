package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;

import java.util.List;

public interface ContainerUnloadingRepository extends JpaRepository<ContainerUnloading, Long> {

    @EntityGraph(attributePaths = {"container", "user"})
    List<ContainerUnloading> findAll();

}
