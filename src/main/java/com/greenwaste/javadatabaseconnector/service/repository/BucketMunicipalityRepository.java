package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import com.greenwaste.javadatabaseconnector.model.Municipality;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

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

    @EntityGraph(attributePaths = {"bucket", "user","user.user"})
    List<BucketMunicipality> findByStatusTrue();
}
