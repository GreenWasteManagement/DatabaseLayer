package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "\"Bucket_Municipality_Container\"")
public class BucketMunicipalityContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Bucket_Municipality_Container_id_gen")
    @SequenceGenerator(name = "Bucket_Municipality_Container_id_gen", sequenceName = "Bucket_Municipality_Container_deposit_id_seq", allocationSize = 1)
    @Column(name = "deposit_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "association_id", nullable = false)
    private BucketMunicipality association;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "container_id", nullable = false)
    private Container container;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "deposittimestamp")
    private Instant deposittimestamp;

    @Column(name = "depositamount", nullable = false, precision = 14, scale = 2)
    private BigDecimal depositamount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Instant getDeposittimestamp() {
        return deposittimestamp;
    }

    public void setDeposittimestamp(Instant deposittimestamp) {
        this.deposittimestamp = deposittimestamp;
    }

    public BigDecimal getDepositamount() {
        return depositamount;
    }

    public void setDepositamount(BigDecimal depositamount) {
        this.depositamount = depositamount;
    }

}