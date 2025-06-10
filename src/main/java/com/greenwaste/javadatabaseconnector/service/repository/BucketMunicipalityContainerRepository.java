package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipalityContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BucketMunicipalityContainerRepository extends JpaRepository<BucketMunicipalityContainer, Long> {


    @Query("""
                SELECT bmc FROM BucketMunicipalityContainer bmc
                JOIN FETCH bmc.association bm
                JOIN FETCH bm.bucket
                JOIN FETCH bm.user m
                JOIN FETCH bmc.container
                WHERE bm.status = true
            """)
    List<BucketMunicipalityContainer> findAllByMunicipalityIdWithActiveAssociation();

    @Query(""" 
            SELECT bmc FROM BucketMunicipalityContainer bmc
            JOIN FETCH bmc.association bm
            JOIN FETCH bm.bucket
            JOIN FETCH bm.user m
            JOIN FETCH bmc.container
            WHERE m.id = :municipalityId""")
    List<BucketMunicipalityContainer> findAllBucketDepositsByMunicipality(@Param("municipalityId") Long municipalityId);

    @Query("SELECT COUNT(bmc) FROM BucketMunicipalityContainer bmc")
    Long countAll();

}
