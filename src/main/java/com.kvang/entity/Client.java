package com.kvang.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kvang on 9/27/17.
 */
@Getter
@Setter
@Accessors
@Entity
@EqualsAndHashCode
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "clientId")
    private int clientId;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_zip_code")
    private String postal_zip_code;

    @Column(name = "email")
    private String email;

    @Column(name = "home_phone")
    private String home_phone;

    @Column(name = "mobile_phone")
    private String mobile_phone;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USstate_stateId")
    private State state;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<ClientNote> clientNotes = new HashSet<ClientNote>(0);

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "clients")
    private Set<Employee> employees = new HashSet<Employee>(0);
}
