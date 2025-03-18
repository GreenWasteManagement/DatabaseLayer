package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "smas")
public class Smas {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "\"position\"")
    private String position;

    @Column(name = "employee_code", nullable = false, length = 50)
    private String employeeCode;

    @Column(name = "citizen_card_code", nullable = false, length = 50)
    private String citizenCardCode;

    @OneToMany(mappedBy = "user")
    private Set<ContainerUnloading> containerUnloadings = new LinkedHashSet<>();

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getCitizenCardCode() {
        return citizenCardCode;
    }

    public void setCitizenCardCode(String citizenCardCode) {
        this.citizenCardCode = citizenCardCode;
    }

    public Set<ContainerUnloading> getContainerUnloadings() {
        return containerUnloadings;
    }

    public void setContainerUnloadings(Set<ContainerUnloading> containerUnloadings) {
        this.containerUnloadings = containerUnloadings;
    }

}