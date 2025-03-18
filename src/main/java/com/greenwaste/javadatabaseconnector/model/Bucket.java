package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucket_id_gen")
    @SequenceGenerator(name = "bucket_id_gen", sequenceName = "bucket_bucket_id_seq", allocationSize = 1)
    @Column(name = "bucket_id", nullable = false)
    private Integer id;

    @Column(name = "capacity", nullable = false, precision = 14, scale = 2)
    private BigDecimal capacity;

    @ColumnDefault("false")
    @Column(name = "is_associated")
    private Boolean isAssociated;

    @OneToMany(mappedBy = "bucket")
    private Set<BucketMunicipality> bucketMunicipalities = new LinkedHashSet<>();

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

    public Boolean getIsAssociated() {
        return isAssociated;
    }

    public void setIsAssociated(Boolean isAssociated) {
        this.isAssociated = isAssociated;
    }

    public Set<BucketMunicipality> getBucketMunicipalities() {
        return bucketMunicipalities;
    }

    public void setBucketMunicipalities(Set<BucketMunicipality> bucketMunicipalities) {
        this.bucketMunicipalities = bucketMunicipalities;
    }

}