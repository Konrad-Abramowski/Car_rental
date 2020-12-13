package org.company.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

@Table
@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @NotBlank(message = "Car`s brand must not be empty!")
    @Column(name = "car_brand")
    private String brand;

    @NotBlank(message = "Car`s color must not be empty!")
    @Column(name = "car_color")
    private String color;

    @NotBlank(message = "Car`s engine type must not be empty!")
    @Column(name = "car_engine_type")
    private String engineType;

    @NotNull
    @Column(name = "car_engine_capacity")
    private float engineCapacity;

    @NotBlank(message = "Car`s country of origin must not be empty!")
    @Column(name = "car_country_of_origin")
    private String countryOfOrigin;

    @NotBlank(message = "Car`s mileage must not be empty!")
    @Column(name = "car_mileage")
    private float mileage;

    @NotNull
    @Column(name = "car_production_year")
    private int productionYear;

    @NotBlank(message = "Car`s vin must not be empty!")
    @Column(name = "car_vin")
    private String vin;

    @NotNull
    @Column(name = "car_price")
    private int price;

    @NotNull
    @Column(name = "car_is_available")
    private boolean isAvailable;

    @OneToMany(mappedBy = "car")
    private Set<Loan> loans;

    public Car() {
    }

    public Car(@NotBlank(message = "Car`s brand must not be empty!") final String brand, @NotBlank(message = "Car`s color must not be empty!") final String color, @NotBlank(message = "Car`s engine type must not be empty!") final String engineType, @NotNull final float engineCapacity, @NotBlank(message = "Car`s country of origin must not be empty!") final String countryOfOrigin, @NotBlank(message = "Car`s mileage must not be empty!") final float mileage, @NotNull final int productionYear, @NotBlank(message = "Car`s vin must not be empty!") final String vin, @NotNull final int price, @NotNull final boolean isAvailable) {
        this.brand = brand;
        this.color = color;
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
        this.countryOfOrigin = countryOfOrigin;
        this.mileage = mileage;
        this.productionYear = productionYear;
        this.vin = vin;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(final String engineType) {
        this.engineType = engineType;
    }

    public float getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(final float engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(final String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(final float mileage) {
        this.mileage = mileage;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(final int productionYear) {
        this.productionYear = productionYear;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(final String vin) {
        this.vin = vin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(final boolean available) {
        isAvailable = available;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(final Set<Loan> loans) {
        this.loans = loans;
    }
}

