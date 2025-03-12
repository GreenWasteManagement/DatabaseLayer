package com.greenwaste.javadatabaseconnector.services;

import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContainerUnloadingService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ContainerUnloading> findAllContainerUnloading() {
        return entityManager.createQuery("FROM ContainerUnloading", ContainerUnloading.class).getResultList();
    }

    public Optional<ContainerUnloading> findContainerUnloadingById(Integer id) {
        return Optional.ofNullable(entityManager.find(ContainerUnloading.class, id));
    }

    @Transactional
    public ContainerUnloading saveContainerUnloading(ContainerUnloading container_unloading) {
        if (container_unloading.getId() == null) {
            entityManager.persist(container_unloading);
            return container_unloading;
        } else {
            return entityManager.merge(container_unloading);
        }
    }

    @Transactional
    public void deleteContainerUnloadingById(Integer id) {
        ContainerUnloading container_unloading = findContainerUnloadingById(id).orElseThrow(() -> new RuntimeException("Descarga de contentor não encontrada!"));
        entityManager.remove(container_unloading);
    }

    public long countContainerUnloading() {
        return (long) entityManager.createQuery("SELECT COUNT(b) FROM ContainerUnloading b").getSingleResult();
    }
}