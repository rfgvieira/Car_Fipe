package com.rfgvieira.Car_Fipe.model;

public class Vehicle {
    private String brand;
    private String year;
    private String model;
    private String fuel;
    private String value;

    @Override
    public String toString() {
        return
                "Modelo='" + brand + " " + model + '\'' +
                ", ano='" + year + '\'' +
                ", fuel='" + fuel + '\'' +
                ", value='" + value;
    }

    public Vehicle(String brand, String year, String model, String fuel, String value) {
        this.brand = brand;
        this.year = year;
        this.model = model;
        this.fuel = fuel;
        this.value = value;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
