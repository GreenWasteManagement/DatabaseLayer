package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ContainerMethods extends JpaRepository<Container, Integer> {

//    // Get all Container
//    List<Container> findAll();

    // Get all containers with said capacity
    @Query("SELECT c FROM Container c WHERE c.capacity = :value")
    List<Container> findContainersByCapacity(@Param("value") BigDecimal value);

//    // Get Container by ID
//    Optional<Container> findById(@Param("container_id") Integer id);
//
//    // Save new BucketMunicipality (Create & Update handled by JpaRepository)
//    <Save extends Container> Save save(Save container);
//
//    // Delete entity by ID
//    void deleteById(Integer id);
}