package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "postal_code")
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postal_code_id_gen")
    @SequenceGenerator(name = "postal_code_id_gen", sequenceName = "postal_code_postal_code_id_seq", allocationSize = 1)
    @Column(name = "postal_code_id", nullable = false)
    private Long id;

    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Column(name = "county", nullable = false, length = 100)
    private String county;

    @Column(name = "district", nullable = false, length = 100)
    private String district;

    @OneToMany(mappedBy = "postalCode")
    private Set<Address> addresses = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

}