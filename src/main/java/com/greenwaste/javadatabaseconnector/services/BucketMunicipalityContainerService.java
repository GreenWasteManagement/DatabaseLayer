package com.greenwaste.javadatabaseconnector.services;

import com.greenwaste.javadatabaseconnector.model.Bucket;
import com.greenwaste.javadatabaseconnector.model.BucketMunicipality;
import com.greenwaste.javadatabaseconnector.model.BucketMunicipalityContainer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BucketMunicipalityContainerService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BucketMunicipalityContainer> findAllBucketMunicipalityContainer() {
        return entityManager.createQuery("FROM BucketMunicipalityContainer", BucketMunicipalityContainer.class).getResultList();
    }

    public Optional<BucketMunicipalityContainer> findBucketMunicipalityContainerById(Integer id) {
        return Optional.ofNullable(entityManager.find(BucketMunicipalityContainer.class, id));
    }

    @Transactional
    public BucketMunicipalityContainer saveBucketMunicipalityContainer(BucketMunicipalityContainer bucket_municipality_container) {
        if (bucket_municipality_container.getId() == null) {
            entityManager.persist(bucket_municipality_container);
            return bucket_municipality_container;
        } else {
            return entityManager.merge(bucket_municipality_container);
        }
    }

    @Transactional
    public void deleteBucketMunicipalityById(Integer id) {
        BucketMunicipalityContainer bucket_municipality_container = findBucketMunicipalityContainerById(id).orElseThrow(() -> new RuntimeException("Relação balde munícipe contentor não encontrada!"));
        entityManager.remove(bucket_municipality_container);
    }

    public long countBucketMunicipalityContainer() {
        return (long) entityManager.createQuery("SELECT COUNT(b) FROM BucketMunicipalityContainer b").getSingleResult();
    }

    @Transactional
    public void insertTestData() {
        BucketMunicipalityContainer bucket1 = new BucketMunicipalityContainer();
        BucketMunicipality municipality1 = new BucketMunicipality();

        saveBucketMunicipalityContainer(bucket1);

        System.out.println("Inserted test data successfully.");
    }
}