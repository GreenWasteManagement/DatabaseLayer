package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "municipality")
public class Municipality {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

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

    public String getCitizenCardCode() {
        return citizenCardCode;
    }

    public void setCitizenCardCode(String citizenCardCode) {
        this.citizenCardCode = citizenCardCode;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Set<BucketMunicipality> getBucketMunicipalities() {
        return bucketMunicipalities;
    }

    public void setBucketMunicipalities(Set<BucketMunicipality> bucketMunicipalities) {
        this.bucketMunicipalities = bucketMunicipalities;
    }

}