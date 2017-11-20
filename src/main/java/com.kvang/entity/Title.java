package com.kvang.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors
@Entity
@EqualsAndHashCode
@Table(name = "Title")
public class Title {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "titleId")
    private int titleId;

    @Column(name = "jobTitle")
    private String jobTitle;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "title", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<Employee>(0);
}
