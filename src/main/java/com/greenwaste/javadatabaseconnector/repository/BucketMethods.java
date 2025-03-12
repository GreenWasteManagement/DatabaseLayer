package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface BucketMethods extends JpaRepository<Bucket, Long> {

    // Get all buckets from municipal
    //@EntityGraph(attributePaths = {"user_id"})
    //List<Bucket> findAll();

    // Get all buckets with said capacity
    @Query("SELECT b FROM Bucket b WHERE b.capacity = :value")
    List<Bucket> findAllByCapacityEquals(@Param("value") BigDecimal value);

//    // Save new bucket (Create & Update handled by JpaRepository)
//    <Save extends Bucket> Save save(Save bucket);
//
//    // Delete entity by ID
//    void deleteById(Long id);
}