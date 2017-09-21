package com.kvang.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kvang on 9/21/17.
 */

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "state")
    private Set<Employee> employees = new HashSet<Employee>(0);

    public State() {
    }

    public State(String state_code, String state_name) {
        this.state_code = state_code;
        this.state_name = state_name;
    }

    public State(String state_code, String state_name, Set<Employee> employees) {
        this.state_code = state_code;
        this.state_name = state_name;
        this.employees = employees;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    /* See if we need this one??????
    @Override
    public String toString() {
        return "State{" +
                "stateId=" + stateId +
                ", state_code='" + state_code + '\'' +
                ", state_name='" + state_name + '\'' +
                ", employees=" + employees +
                '}';
    }
    */
}