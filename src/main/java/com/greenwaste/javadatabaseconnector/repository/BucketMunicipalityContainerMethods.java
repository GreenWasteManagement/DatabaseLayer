package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import com.greenwaste.javadatabaseconnector.model.BucketMunicipalityContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BucketMunicipalityContainerMethods extends JpaRepository<BucketMunicipalityContainer, Integer> {

//    // Get all BucketMunicipalityContainer
//    List<BucketMunicipalityContainer> findAll();

    // Get all deposits with said amount
    @Query("SELECT d FROM BucketMunicipalityContainer d WHERE d.depositamount = :value")
    List<BucketMunicipalityContainer> findAllByDepositamountEquals(@Param("value") Long value);

//    // Get BucketMunicipalityContainer by ID
//    Optional<BucketMunicipalityContainer> findById(@Param("deposit_id") Integer id);
//
//    // Save new BucketMunicipalityContainer (Create & Update handled by JpaRepository)
//    <Save extends BucketMunicipalityContainer> Save save(Save bucketmunicipalitycontainer);
//
//    // Delete entity by ID
//    void deleteById(Integer id);
}