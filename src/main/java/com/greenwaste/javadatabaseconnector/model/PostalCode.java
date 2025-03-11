package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"PostalCode\"")
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PostalCode_id_gen")
    @SequenceGenerator(name = "PostalCode_id_gen", sequenceName = "PostalCode_postal_code_id_seq", allocationSize = 1)
    @Column(name = "postal_code_id", nullable = false)
    private Integer id;

    @Column(name = "postalcode", nullable = false, length = 20)
    private String postalcode;

    @Column(name = "county", nullable = false, length = 100)
    private String county;

    @Column(name = "district", nullable = false, length = 100)
    private String district;

    @OneToMany(mappedBy = "postalCode")
    private Set<Address> addresses = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
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