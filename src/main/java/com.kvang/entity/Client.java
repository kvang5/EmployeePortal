package com.kvang.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by kvang on 9/27/17.
 */

@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "clientId")
    private int employeeId;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USstates_stateId")
    private State state;

    public Client() {

    }
}
