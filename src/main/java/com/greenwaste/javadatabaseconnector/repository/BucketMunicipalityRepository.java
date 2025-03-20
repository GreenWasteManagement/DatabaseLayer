package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import com.greenwaste.javadatabaseconnector.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BucketMunicipalityRepository extends JpaRepository<BucketMunicipality, Long> {
    List<BucketMunicipality> findByUserIdAndStatus(Long userId, Boolean status);
    List<BucketMunicipality> findByBucketIdAndStatus(Long bucketId, Boolean status);
    Optional<BucketMunicipality> findFirstByUserAndStatusTrue(Municipality user);
}
