package com.kvang.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kvang on 9/19/17.
 */
@Setter
@Getter
@Accessors
@ToString
@Entity
@Table(name = "Employee")
public class Employee implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "employeeId")
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USstate_stateId")
    private State state;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Title_titleId")
    private Title title;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<EmployeeRole> employees = new HashSet<EmployeeRole>(0);

    @Column(name = "status")
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<ClientNote> clientNotes = new HashSet<ClientNote>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "EmployeeClient", joinColumns = {
            @JoinColumn(name = "employeeId", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "clientId",
                    nullable = false, updatable = false) })
    private Set<Client> clientSet = new HashSet<Client>(0);

    /*public void addClient(Client client) {
        this.clients.add(client);
    }*/
}
