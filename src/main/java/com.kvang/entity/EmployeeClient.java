package com.kvang.entity;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@Table(name = "EmployeeClient")
public class EmployeeClient implements java.io.Serializable {



}
