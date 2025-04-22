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
@Table(name = "bucket_municipality_container")
public class BucketMunicipalityContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucket_municipality_container_id_gen")
    @SequenceGenerator(name = "bucket_municipality_container_id_gen", sequenceName = "bucket_municipality_container_deposit_id_seq", allocationSize = 1)
    @Column(name = "deposit_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "association_id", nullable = false)
    private BucketMunicipality association;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "container_id", nullable = false)
    private Container container;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "deposit_timestamp")
    private Instant depositTimestamp;

    @Column(name = "deposit_amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal depositAmount;

       /*
    @Version
    private Long version;
*/
}