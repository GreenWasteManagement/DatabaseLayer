package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
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

}