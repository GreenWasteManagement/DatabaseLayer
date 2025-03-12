package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ContainerUnloadingMethods extends JpaRepository<ContainerUnloading, Integer> {

//    // Get all ContainerUnloading
//    List<ContainerUnloading> findAll();

    // Get all container unloadings with said quantity
    @Query("SELECT c FROM ContainerUnloading c WHERE c.unloadedquantity = :value")
    List<ContainerUnloading> findContainerUnloadingsBy(@Param("value") BigDecimal value);

//    // Get ContainerUnloading by ID
//    Optional<ContainerUnloading> findById(@Param("discharge_id") Integer id);
//
//    // Save new BucketMunicipality (Create & Update handled by JpaRepository)
//    <Save extends ContainerUnloading> Save save(Save containerunloading);
//
//    // Delete entity by ID
//    void deleteById(Integer id);
}