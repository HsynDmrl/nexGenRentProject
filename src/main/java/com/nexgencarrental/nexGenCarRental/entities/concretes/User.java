package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Customer;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "gsm")
    private String gsm;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Customer> customers;

    @OneToMany(mappedBy = "user")
    private List<Employee> employees;



}
