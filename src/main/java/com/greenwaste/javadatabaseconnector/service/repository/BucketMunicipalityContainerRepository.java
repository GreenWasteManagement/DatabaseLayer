package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipalityContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BucketMunicipalityContainerRepository extends JpaRepository<BucketMunicipalityContainer, Long> {

    @Query("""
    SELECT bmc FROM BucketMunicipalityContainer bmc
    JOIN FETCH bmc.association bm
    JOIN FETCH bm.bucket b
    JOIN FETCH bm.user m
    JOIN FETCH m.user u
    JOIN FETCH u.address a
    JOIN FETCH a.postalCode pc
    WHERE b.isAssociated = true
""")
    List<BucketMunicipalityContainer> findAllActiveWithRelations();


    @Query("SELECT COUNT(bmc) FROM BucketMunicipalityContainer bmc")
    Long countAll();

}
