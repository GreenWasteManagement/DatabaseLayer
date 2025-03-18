package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BucketMunicipalityMethods extends JpaRepository<BucketMunicipality, Integer> {

//    // Get all BucketMunicipality
//    List<BucketMunicipality> findAll();

    // Get all deposits on a date
    @Query("SELECT b FROM BucketMunicipality b WHERE b.timestampofassociation = :value")
    List<BucketMunicipality> findBucketMunicipalitiesBy(@Param("value") Date value);

//    // Get BucketMunicipality by ID
//    Optional<BucketMunicipality> findById(@Param("association_id") Integer id);
//
//    // Save new BucketMunicipality (Create & Update handled by JpaRepository)
//    <Save extends BucketMunicipality> Save save(Save bucketmunicipality);
//
//    // Delete entity by ID
//    void deleteById(Integer id);
}