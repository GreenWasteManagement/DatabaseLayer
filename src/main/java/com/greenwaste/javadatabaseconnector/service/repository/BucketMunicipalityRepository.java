package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import com.greenwaste.javadatabaseconnector.model.BucketMunicipalityContainer;
import com.greenwaste.javadatabaseconnector.model.Municipality;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BucketMunicipalityRepository extends JpaRepository<BucketMunicipality, Long> {
    List<BucketMunicipality> findByUserIdAndStatus(Long userId, Boolean status);

    List<BucketMunicipality> findByBucketIdAndStatus(Long bucketId, Boolean status);

    @EntityGraph(attributePaths = {"bucket", "user"})
    Optional<BucketMunicipality> findById(Long id);

    @EntityGraph(attributePaths = {"bucket", "user"})
    List<BucketMunicipality> findAll();

    Optional<BucketMunicipality> findFirstByUserAndStatusTrue(Municipality user);

    @Query("""
                SELECT bm FROM BucketMunicipality bm
                JOIN FETCH bm.bucket
                JOIN FETCH bm.user u
                JOIN FETCH u.user
                WHERE bm.status = true
            """)
    List<BucketMunicipality> findByStatusTrue();



    @Query("""
    SELECT bm FROM BucketMunicipality bm
    JOIN FETCH bm.bucket b
    JOIN FETCH bm.user m
    JOIN FETCH m.user u
    JOIN FETCH u.address a
    JOIN FETCH a.postalCode pc
    WHERE bm.status = true
""")
    List<BucketMunicipality> findAllActiveWithRelations();


}
