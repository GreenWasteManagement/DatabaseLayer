package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BucketMunicipality getAssociation() {
        return association;
    }

    public void setAssociation(BucketMunicipality association) {
        this.association = association;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Instant getDepositTimestamp() {
        return depositTimestamp;
    }

    public void setDepositTimestamp(Instant depositTimestamp) {
        this.depositTimestamp = depositTimestamp;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

}