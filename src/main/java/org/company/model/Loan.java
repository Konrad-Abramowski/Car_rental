package org.company.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private int id;

    @NotNull
    @Column(name = "loan_start_date")
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "loan_end_date")
    private LocalDateTime endDate;

    @NotNull
    @Column(name = "loan_price")
    private float price;

    @OneToOne
    @MapsId
    @NotNull
    private Client client;

    @OneToOne
    @MapsId
    @NotNull
    private Car car;

    public Loan() {
    }

    public Loan(@NotNull final LocalDateTime startDate, @NotNull final LocalDateTime endDate, @NotNull final float price, @NotNull final Client client, @NotNull final Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.client = client;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(final float price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(final Car car) {
        this.car = car;
    }
}
