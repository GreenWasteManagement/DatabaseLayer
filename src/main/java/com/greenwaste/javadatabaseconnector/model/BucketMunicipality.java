package com.greenwaste.javadatabaseconnector.model;

import com.greenwaste.javadatabaseconnector.dtos.olderandnotusednow.bucketwebdto.older.UpdateBucketRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
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



       /*
    @Version
    private Long version;
*/
}