package com.greenwaste.javadatabaseconnector.service.repository;

import com.greenwaste.javadatabaseconnector.model.BucketMunicipalityContainer;
import com.greenwaste.javadatabaseconnector.model.Container;
import com.greenwaste.javadatabaseconnector.model.ContainerUnloading;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedHashSet;
import java.util.Set;

public interface ContainerRepository extends JpaRepository<Container, Long> {

    @OneToMany(mappedBy = "container", fetch = FetchType.EAGER)
    Set<BucketMunicipalityContainer> bucketMunicipalityContainers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "container", fetch = FetchType.EAGER)
    Set<ContainerUnloading> containerUnloadings = new LinkedHashSet<>();

}
