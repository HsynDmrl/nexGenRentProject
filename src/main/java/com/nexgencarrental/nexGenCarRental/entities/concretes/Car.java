package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {

    @Column(name ="kilometer")
    private double kilometer;

    @Column(name = "year")
    private int year;

    @Column(name ="daily_price")
    private double dailyPrice;

    @Column(name = "plate",unique = true)
    private String plate;

    @ManyToOne
    @JoinColumn(name ="model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name ="color_id")
    private Color color;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;

}
