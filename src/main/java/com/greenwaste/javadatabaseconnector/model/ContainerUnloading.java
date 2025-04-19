package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "container_unloading")
public class ContainerUnloading {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_unloading_id_gen")
    @SequenceGenerator(name = "container_unloading_id_gen", sequenceName = "container_unloading_discharge_id_seq", allocationSize = 1)
    @Column(name = "discharge_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "container_id", nullable = false)
    private Container container;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Smas user;

    @Column(name = "unloaded_quantity", nullable = false, precision = 14, scale = 2)
    private BigDecimal unloadedQuantity;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "unloading_timestamp")
    private Instant unloadingTimestamp;

}