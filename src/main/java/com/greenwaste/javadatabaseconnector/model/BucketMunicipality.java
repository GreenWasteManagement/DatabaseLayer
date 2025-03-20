package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bucket_municipality")
public class BucketMunicipality {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucket_municipality_id_gen")
    @SequenceGenerator(name = "bucket_municipality_id_gen", sequenceName = "bucket_municipality_association_id_seq", allocationSize = 1)
    @Column(name = "association_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private Municipality user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "bucket_id", nullable = false)
    private Bucket bucket;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "timestamp_of_association")
    private Instant timestampOfAssociation;

    @ColumnDefault("false")
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "association")
    private Set<BucketMunicipalityContainer> bucketMunicipalityContainers = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Municipality getUser() {
        return user;
    }

    public void setUser(Municipality user) {
        this.user = user;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Instant getTimestampOfAssociation() {
        return timestampOfAssociation;
    }

    public void setTimestampOfAssociation(Instant timestampOfAssociation) {
        this.timestampOfAssociation = timestampOfAssociation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Set<BucketMunicipalityContainer> getBucketMunicipalityContainers() {
        return bucketMunicipalityContainers;
    }

    public void setBucketMunicipalityContainers(Set<BucketMunicipalityContainer> bucketMunicipalityContainers) {
        this.bucketMunicipalityContainers = bucketMunicipalityContainers;
    }

}