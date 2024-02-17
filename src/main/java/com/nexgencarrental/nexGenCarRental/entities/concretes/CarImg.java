package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "car_images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarImg extends BaseEntity {
    private String imageUrl;
    private String publicId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car; // CarImage, Car ile bir çoktan bire ilişki içindedir.
}
