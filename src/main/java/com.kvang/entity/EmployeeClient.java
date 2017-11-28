package com.kvang.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.AssociationOverride;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Accessors
@ToString
@Entity
@Table(name = "EmployeeClient")
@AssociationOverride(name = "pk.")
public class EmployeeClient implements java.io.Serializable {

    private Employee employeeId;

    //private

}
