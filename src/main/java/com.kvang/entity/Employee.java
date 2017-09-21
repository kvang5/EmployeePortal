package com.kvang.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by kvang on 9/19/17.
 */

@Entity
@Table (name = "Employees")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USstates_stateId")
    private State state;

    public Employee() {
    }

    public Employee(String first_name, String last_name, String address1, String address2, String city, String postal_zip_code, String email, String home_phone, String mobile_phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postal_zip_code = postal_zip_code;
        this.email = email;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
    }

    public Employee(String first_name, String last_name, String address1, String address2, String city, String postal_zip_code, String email, String home_phone, String mobile_phone, State state) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postal_zip_code = postal_zip_code;
        this.email = email;
        this.home_phone = home_phone;
        this.mobile_phone = mobile_phone;
        this.state = state;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_zip_code() {
        return postal_zip_code;
    }

    public void setPostal_zip_code(String postal_zip_code) {
        this.postal_zip_code = postal_zip_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /*
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", postal_zip_code='" + postal_zip_code + '\'' +
                ", email='" + email + '\'' +
                ", home_phone='" + home_phone + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", state=" + state +
                '}';
    }
    */
}
