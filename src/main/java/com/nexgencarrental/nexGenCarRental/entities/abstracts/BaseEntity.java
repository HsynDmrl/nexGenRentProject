package com.nexgencarrental.nexGenCarRental.entities.abstracts;

import com.nexgencarrental.nexGenCarRental.core.utilities.Constants.DatabaseConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @Column(name = DatabaseConstants.ID_COLUMN)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = DatabaseConstants.CREATED_DATE_COLUMN)
    private LocalDate createdDate;

    @Column(name = DatabaseConstants.UPDATED_DATE_COLUMN, nullable = true)
    private LocalDate updatedDate;

    @PrePersist
    private void beforeAdd() {
        if (createdDate == null) {
            createdDate = LocalDate.now();
        }
    }

    @PreUpdate
    private void beforeUpdate() {
        updatedDate = LocalDate.now();
    }
}
