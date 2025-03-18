package com.greenwaste.javadatabaseconnector.service;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import com.greenwaste.javadatabaseconnector.repository.BucketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BucketService {

    private final BucketRepository bucketRepository;

    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    public List<Bucket> getAllBuckets() {
        return bucketRepository.findAll();
    }

    public Bucket getBucketById(Long id) {
        return bucketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bucket not found"));
    }

    @Transactional
    public Bucket createBucket(Bucket bucket) {
        return bucketRepository.save(bucket);
    }

    @Transactional
    public Bucket updateBucket(Long id, Bucket updatedBucket) {
        Bucket existing = getBucketById(id);
        existing.setCapacity(updatedBucket.getCapacity());
        existing.setIsAssociated(updatedBucket.getIsAssociated());
        return bucketRepository.save(existing);
    }

    @Transactional
    public void deleteBucket(Long id) {
        bucketRepository.deleteById(id);
    }
}
