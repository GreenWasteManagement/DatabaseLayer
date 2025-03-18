package com.greenwaste.javadatabaseconnector.services;

import com.greenwaste.javadatabaseconnector.model.Container;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContainerService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Container> findAllContainer() {
        return entityManager.createQuery("FROM Container", Container.class).getResultList();
    }

    public Optional<Container> findContainerById(Integer id) {
        return Optional.ofNullable(entityManager.find(Container.class, id));
    }

    @Transactional
    public Container saveContainer(Container container) {
        if (container.getId() == null) {
            entityManager.persist(container);
            return container;
        } else {
            return entityManager.merge(container);
        }
    }

    @Transactional
    public void deleteContainerById(Integer id) {
        Container container = findContainerById(id).orElseThrow(() -> new RuntimeException("Contentor não encontrado!"));
        entityManager.remove(container);
    }

    public long countContainer() {
        return (long) entityManager.createQuery("SELECT COUNT(b) FROM Container b").getSingleResult();
    }
}