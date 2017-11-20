package com.kvang.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Accessors
@Entity
@EqualsAndHashCode
@Table(name = "EmployeeRole")
public class EmployeeRole {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "employee_rolesId")
    private int employee_rolesId;

    @Column(name = "email")
    private String email;

    @Column(name = "role_name")
    private String role_name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Employee_employeeId")
    private Employee employee;

}
