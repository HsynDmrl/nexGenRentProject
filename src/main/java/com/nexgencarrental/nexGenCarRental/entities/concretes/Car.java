package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {
//test mustafa

    @Column(name = "kilometer")
    private double kilometer;

    @Column(name = "year")
    private short year;

    @Column(name = "daily_price")
    private double dailyPrice;

    @Column(name = "plate", unique = true)
    private String plate;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "is_status")
    private boolean isStatus;

    @Column(name = "gear_type")
    @Enumerated(EnumType.STRING)
    private GearType gearType;

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonIgnoreProperties("cars")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CarImg> images = new ArrayList<>(); // Bir arabanın birden fazla resmi olabilir.

}
