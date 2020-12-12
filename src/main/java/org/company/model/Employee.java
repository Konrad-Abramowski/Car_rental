package org.company.model;

import org.company.model.enums.Job;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @NotNull
    @Column(name = "employee_pesel")
    private long pesel;

    @NotBlank(message = "Employee`s first name must not be empty!")
    @Column(name = "employee_first_name")
    private String firstName;


    @NotBlank(message = "Employee`s last name must not be empty!")
    @Column(name = "employee_last_name")
    private String lastName;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_pesel")
    private Job job;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @NotNull
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @NotNull
    private Account account;

    public Employee() {
    }

    public Employee(@NotNull final long pesel, @NotBlank(message = "Employee`s first name must not be empty!") final String firstName, @NotBlank(message = "Employee`s last name must not be empty!") final String lastName, @NotNull final Job job, @NotNull final Address address, @NotNull final Account account) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.address = address;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public long getPesel() {
        return pesel;
    }

    public void setPesel(final long pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(final Job job) {
        this.job = job;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(final Account account) {
        this.account = account;
    }
}
