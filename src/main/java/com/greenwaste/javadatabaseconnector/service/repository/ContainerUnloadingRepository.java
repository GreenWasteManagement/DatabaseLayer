package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContainerUnloadingRepository extends JpaRepository<ContainerUnloading, Long> {

    @EntityGraph(attributePaths = {"container", "user"})
    List<ContainerUnloading> findAll();


    @Query("SELECT COUNT(cu) FROM ContainerUnloading cu")
    Long countAll();

    @Query("SELECT SUM(cu.unloadedQuantity) FROM ContainerUnloading cu")
    Long sumAllUnloadedQuantities();


}
