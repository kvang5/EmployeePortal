package com.kvang.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    public Title() {
    }

    public Title(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Title(String jobTitle, Set<Employee> employees) {
        this.jobTitle = jobTitle;
        this.employees = employees;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Title{" +
                "titleId=" + titleId +
                ", jobTitle='" + jobTitle + '\'' +
                ", employees=" + employees +
                '}';
    }
}
