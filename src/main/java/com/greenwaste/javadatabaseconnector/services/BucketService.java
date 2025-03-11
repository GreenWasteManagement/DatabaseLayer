package com.greenwaste.javadatabaseconnector.services;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BucketService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Bucket saveBucket(Bucket bucket) {
        if (bucket.getId() == null) {
            entityManager.persist(bucket);
            return bucket;
        } else {
            return entityManager.merge(bucket);
        }
    }

    public Optional<Bucket> findBucketById(Integer id) {
        return Optional.ofNullable(entityManager.find(Bucket.class, id));
    }

    public List<Bucket> findAllBuckets() {
        return entityManager.createQuery("FROM Bucket", Bucket.class).getResultList();
    }

    @Transactional
    public void deleteBucketById(Integer id) {
        Bucket bucket = findBucketById(id).orElseThrow(() -> new RuntimeException("Bucket não encontrado"));
        entityManager.remove(bucket);
    }

    public long countBuckets() {
        return (long) entityManager.createQuery("SELECT COUNT(b) FROM Bucket b").getSingleResult();
    }
}
