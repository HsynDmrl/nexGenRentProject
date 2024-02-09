package com.nexgencarrental.nexGenCarRental.entities.concretes;

public enum FuelType {
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid"),
    GASOLINE("Gasoline");

    private final String fuel;

    FuelType(String fuel) {
        this.fuel = fuel;
    }

    public String getFuelType() {
        return fuel;
    }
}