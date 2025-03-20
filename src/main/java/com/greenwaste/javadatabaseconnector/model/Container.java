package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_id_gen")
    @SequenceGenerator(name = "container_id_gen", sequenceName = "container_container_id_seq", allocationSize = 1)
    @Column(name = "container_id", nullable = false)
    private Long id;

    @Column(name = "capacity", nullable = false, precision = 14, scale = 2)
    private BigDecimal capacity;

    @Column(name = "localization", nullable = false)
    private String localization;

    @ColumnDefault("0")
    @Column(name = "current_volume_level", precision = 14, scale = 2)
    private BigDecimal currentVolumeLevel;

    @OneToMany(mappedBy = "container")
    private Set<BucketMunicipalityContainer> bucketMunicipalityContainers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "container")
    private Set<ContainerUnloading> containerUnloadings = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public BigDecimal getCurrentVolumeLevel() {
        return currentVolumeLevel;
    }

    public void setCurrentVolumeLevel(BigDecimal currentVolumeLevel) {
        this.currentVolumeLevel = currentVolumeLevel;
    }

    public Set<BucketMunicipalityContainer> getBucketMunicipalityContainers() {
        return bucketMunicipalityContainers;
    }

    public void setBucketMunicipalityContainers(Set<BucketMunicipalityContainer> bucketMunicipalityContainers) {
        this.bucketMunicipalityContainers = bucketMunicipalityContainers;
    }

    public Set<ContainerUnloading> getContainerUnloadings() {
        return containerUnloadings;
    }

    public void setContainerUnloadings(Set<ContainerUnloading> containerUnloadings) {
        this.containerUnloadings = containerUnloadings;
    }

}