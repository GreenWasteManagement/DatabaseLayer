package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "\"Address\"")
public class Address {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "floordetails")
    private String floordetails;

    @Column(name = "floornumber")
    private Integer floornumber;

    @Column(name = "doornumber")
    private Integer doornumber;

    @Column(name = "street")
    private String street;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "postal_code_id", nullable = false)
    private PostalCode postalCode;

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

    public String getFloordetails() {
        return floordetails;
    }

    public void setFloordetails(String floordetails) {
        this.floordetails = floordetails;
    }

    public Integer getFloornumber() {
        return floornumber;
    }

    public void setFloornumber(Integer floornumber) {
        this.floornumber = floornumber;
    }

    public Integer getDoornumber() {
        return doornumber;
    }

    public void setDoornumber(Integer doornumber) {
        this.doornumber = doornumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }

}