package com.greenwaste.javadatabaseconnector.services;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BucketMunicipalityService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BucketMunicipality> findAllBucketMunicipality() {
        return entityManager.createQuery("FROM BucketMunicipality", BucketMunicipality.class).getResultList();
    }

    public Optional<BucketMunicipality> findBucketMunicipalityById(Integer id) {
        return Optional.ofNullable(entityManager.find(BucketMunicipality.class, id));
    }

    @Transactional
    public BucketMunicipality saveBucketMunicipality(BucketMunicipality bucket_municipality) {
        if (bucket_municipality.getId() == null) {
            entityManager.persist(bucket_municipality);
            return bucket_municipality;
        } else {
            return entityManager.merge(bucket_municipality);
        }
    }

    @Transactional
    public void deleteBucketMunicipalityById(Integer id) {
        BucketMunicipality bucket_municipality = findBucketMunicipalityById(id).orElseThrow(() -> new RuntimeException("Relação balde munícipe não encontrada!"));
        entityManager.remove(bucket_municipality);
    }

    public long countBucketMunicipality() {
        return (long) entityManager.createQuery("SELECT COUNT(b) FROM BucketMunicipality b").getSingleResult();
    }
}