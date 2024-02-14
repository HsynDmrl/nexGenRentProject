package com.nexgencarrental.nexGenCarRental.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "logo_path")
    private String logoPath;

    @OneToMany(mappedBy = "brand")
    @JsonIgnoreProperties("brand")
    private List<Model> models;


}
