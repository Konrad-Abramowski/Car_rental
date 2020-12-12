package org.company.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Table
@Entity(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;

    @NotBlank(message = "Address` country must not be empty!")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "Address` city must not be empty!")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "Address` street name must not be empty!")
    @Column(name = "street_name")
    private String streetName;

    @NotBlank(message = "Address` house number must not be empty!")
    @Column(name = "house_number")
    private String houseNumber;

    @NotNull
    @Column(name = "postal_code")
    private int postalCode;


    public Address() {
    }

    public Address(@NotNull final String country, @NotNull final String city, @NotNull final String streetName, @NotNull final String houseNumber, @NotNull final int postalCode) {
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(final String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(final String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final int postalCode) {
        this.postalCode = postalCode;
    }
}
