package com.kvang.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kvang on 9/21/17.
 */
@Getter
@Setter
@Accessors
@Entity
@Table(name = "USstates")
public class State implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "stateId")
    private int stateId;

    @Column(name = "state_code")
    private String state_code;

    @Column(name = "state_name")
    private String state_name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<Employee>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state", cascade = CascadeType.ALL)
    private Set<Client> clients = new HashSet<Client>(0);
}
