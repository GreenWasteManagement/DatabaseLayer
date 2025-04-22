package com.greenwaste.javadatabaseconnector.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
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


       /*
    @Version
    private Long version;
*/
}