package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "municipality")
public class Municipality {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "citizen_card_code", nullable = false, length = 50)
    private String citizenCardCode;

    @Column(name = "nif", nullable = false, length = 9)
    private String nif;

    @OneToMany(mappedBy = "user")
    private Set<BucketMunicipality> bucketMunicipalities = new LinkedHashSet<>();

}