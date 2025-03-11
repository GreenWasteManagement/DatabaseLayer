package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_id_gen")
    @SequenceGenerator(name = "User_id_gen", sequenceName = "User_user_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phonenumber", length = 16)
    private String phonenumber;

    @OneToOne(mappedBy = "user")
    private Address address;

    @OneToOne(mappedBy = "user")
    private Admin admin;

    @OneToMany(mappedBy = "user")
    private Set<BucketMunicipality> bucketMunicipalities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ContainerUnloading> containerUnloadings = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user")
    private Municipality municipality;
    @OneToOne(mappedBy = "user")
    private Smas smas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Set<BucketMunicipality> getBucketMunicipalities() {
        return bucketMunicipalities;
    }

    public void setBucketMunicipalities(Set<BucketMunicipality> bucketMunicipalities) {
        this.bucketMunicipalities = bucketMunicipalities;
    }

    public Set<ContainerUnloading> getContainerUnloadings() {
        return containerUnloadings;
    }

    public void setContainerUnloadings(Set<ContainerUnloading> containerUnloadings) {
        this.containerUnloadings = containerUnloadings;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Smas getSma() {
        return smas;
    }

    public void setSma(Smas smas) {
        this.smas = smas;
    }

/*
 TODO [Reverse Engineering] create field to map the 'role' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "role", columnDefinition = "user_role not null")
    private Object role;
*/
}