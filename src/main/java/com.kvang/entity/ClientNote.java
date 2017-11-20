package com.kvang.entity;

import com.kvang.utility.LocalDateAttributeConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by kvang on 9/27/17.
 */
@Getter
@Setter
@Accessors
@Entity
@Table(name = "ClientNote")
public class ClientNote implements java.io.Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "client_noteId")
    private int client_noteId;

    @Column(name = "date")
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate date;

    @Column(name = "care_time")
    private Double care_time;

    @Column(name = "description")
    private String description;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Employee_employeeId")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Client_clientId")
    private Client client;

}
