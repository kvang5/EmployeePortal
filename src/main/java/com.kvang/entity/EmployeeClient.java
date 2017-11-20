package com.kvang.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Accessors
@ToString
@Entity
@Table(name = "EmployeeClient")
public class EmployeeClient implements java.io.Serializable {



}
