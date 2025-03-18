package com.greenwaste.javadatabaseconnector.repository;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
