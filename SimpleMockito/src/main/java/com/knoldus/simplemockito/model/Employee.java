package com.knoldus.simplemockito.model;

import javax.persistence.*;

@Entity
@Table(name = "employeedata")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fullname;
    private int age;
    private String location;
    private String designation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
