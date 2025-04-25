package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, Long> {


    @Query("""
            SELECT DISTINCT b
            FROM Bucket b
            LEFT JOIN FETCH b.bucketMunicipalities bm
            LEFT JOIN FETCH bm.user m
            LEFT JOIN FETCH m.user u
            """)
    List<Bucket> findAllBucketsWithMunicipalityInfo();

}
