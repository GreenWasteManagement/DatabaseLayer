package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"Bucket_Municipality\"")
public class BucketMunicipality {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Bucket_Municipality_id_gen")
    @SequenceGenerator(name = "Bucket_Municipality_id_gen", sequenceName = "Bucket_Municipality_association_id_seq", allocationSize = 1)
    @Column(name = "association_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "bucket_id", nullable = false)
    private Bucket bucket;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "timestampofassociation")
    private Instant timestampofassociation;

    @OneToMany(mappedBy = "association")
    private Set<BucketMunicipalityContainer> bucketMunicipalityContainers = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Instant getTimestampofassociation() {
        return timestampofassociation;
    }

    public void setTimestampofassociation(Instant timestampofassociation) {
        this.timestampofassociation = timestampofassociation;
    }

    public Set<BucketMunicipalityContainer> getBucketMunicipalityContainers() {
        return bucketMunicipalityContainers;
    }

    public void setBucketMunicipalityContainers(Set<BucketMunicipalityContainer> bucketMunicipalityContainers) {
        this.bucketMunicipalityContainers = bucketMunicipalityContainers;
    }

/*
 TODO [Reverse Engineering] create field to map the 'status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "status", columnDefinition = "association_status not null")
    private Object status;
*/
}