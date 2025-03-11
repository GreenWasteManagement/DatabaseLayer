package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"Container\"")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Container_id_gen")
    @SequenceGenerator(name = "Container_id_gen", sequenceName = "Container_container_id_seq", allocationSize = 1)
    @Column(name = "container_id", nullable = false)
    private Integer id;

    @Column(name = "capacity", nullable = false, precision = 14, scale = 2)
    private BigDecimal capacity;

    @Column(name = "localization", nullable = false)
    private String localization;

    @ColumnDefault("0")
    @Column(name = "currentvolumelevel", precision = 14, scale = 2)
    private BigDecimal currentvolumelevel;

    @OneToMany(mappedBy = "container")
    private Set<BucketMunicipalityContainer> bucketMunicipalityContainers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "container")
    private Set<ContainerUnloading> containerUnloadings = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BigDecimal getCurrentvolumelevel() {
        return currentvolumelevel;
    }

    public void setCurrentvolumelevel(BigDecimal currentvolumelevel) {
        this.currentvolumelevel = currentvolumelevel;
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