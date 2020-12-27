package com.harunugur;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Employee")
@Inheritance( strategy = InheritanceType.JOINED )
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_name",nullable = false,length = 40,unique = false,
    updatable = true,insertable = true)
    private String name;

    @ManyToOne
    private Department department;

    public Employee() {
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
