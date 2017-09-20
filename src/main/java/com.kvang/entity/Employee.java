package com.kvang.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by kvang on 9/19/17.
 */

@Entity
@Table (name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

}
