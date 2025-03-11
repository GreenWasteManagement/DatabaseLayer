package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "\"Container_Unloading\"")
public class ContainerUnloading {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Container_Unloading_id_gen")
    @SequenceGenerator(name = "Container_Unloading_id_gen", sequenceName = "Container_Unloading_discharge_id_seq", allocationSize = 1)
    @Column(name = "discharge_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "container_id", nullable = false)
    private Container container;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "unloadedquantity", nullable = false, precision = 14, scale = 2)
    private BigDecimal unloadedquantity;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "unloadingtimestamp")
    private Instant unloadingtimestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getUnloadedquantity() {
        return unloadedquantity;
    }

    public void setUnloadedquantity(BigDecimal unloadedquantity) {
        this.unloadedquantity = unloadedquantity;
    }

    public Instant getUnloadingtimestamp() {
        return unloadingtimestamp;
    }

    public void setUnloadingtimestamp(Instant unloadingtimestamp) {
        this.unloadingtimestamp = unloadingtimestamp;
    }

}