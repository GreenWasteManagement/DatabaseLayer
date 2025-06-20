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
@Table(name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucket_id_gen")
    @SequenceGenerator(name = "bucket_id_gen", sequenceName = "bucket_bucket_id_seq", allocationSize = 1)
    @Column(name = "bucket_id", nullable = false)
    private Long id;

    @Column(name = "capacity", nullable = false, precision = 14, scale = 2)
    private BigDecimal capacity;

    @ColumnDefault("false")
    @Column(name = "is_associated")
    private Boolean isAssociated;

    @OneToMany(mappedBy = "bucket")
    private Set<BucketMunicipality> bucketMunicipalities = new LinkedHashSet<>();


       /*
    @Version
    private Long version;
*/
}