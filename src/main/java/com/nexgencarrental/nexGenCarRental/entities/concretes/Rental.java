package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rentals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental extends BaseEntity {

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "start_kilometer")
    private double startKilometer;

    @Column(name = "end_kilometer")
    private Double endKilometer;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "discount")
    private double discount;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "rental")
    private List<Invoice> invoices;
}
