package com.greenwaste.javadatabaseconnector.services;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BucketService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Bucket> findAllBuckets() {
        return entityManager.createQuery("FROM Bucket", Bucket.class).getResultList();
    }

    public Optional<Bucket> findBucketById(Integer id) {
        return Optional.ofNullable(entityManager.find(Bucket.class, id));
    }

    @Transactional
    public Bucket saveBucket(Bucket bucket) {
        if (bucket.getId() == null) {
            entityManager.persist(bucket);
            return bucket;
        } else {
            return entityManager.merge(bucket);
        }
    }

    @Transactional
    public void deleteBucketById(Integer id) {
        Bucket bucket = findBucketById(id).orElseThrow(() -> new RuntimeException("Balde não encontrado!"));
        entityManager.remove(bucket);
    }

    public long countBuckets() {
        return (long) entityManager.createQuery("SELECT COUNT(b) FROM Bucket b").getSingleResult();
    }

    @Transactional
    public void insertTestData() {
        Bucket bucket1 = new Bucket();
        bucket1.setIsassociated(true);
        bucket1.setCapacity(BigDecimal.valueOf(50));

        Bucket bucket2 = new Bucket();
        bucket2.setIsassociated(true);
        bucket2.setCapacity(BigDecimal.valueOf(50));

        saveBucket(bucket1);
        saveBucket(bucket2);

        System.out.println("Inserted test data successfully.");
    }

}