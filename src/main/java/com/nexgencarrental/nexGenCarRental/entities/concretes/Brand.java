package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;



}
