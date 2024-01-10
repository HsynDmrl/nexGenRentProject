package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="colors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "color")
    private List<Car> cars;
}
