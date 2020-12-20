package org.company.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table
@Entity(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int id;

    @NotBlank(message = "Client`s first name must not be empty!")
    @Column(name = "client_first_name")
    private String firstName;

    @NotBlank(message = "Client`s last name must not be empty!")
    @Column(name = "client_last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @NotNull
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @NotNull
    private Address address;

    @OneToMany(mappedBy = "client")
    private Set<Loan> loans;

    public Client() {
    }

    public Client(@NotBlank(message = "Client`s first name must not be empty!") final String firstName, @NotBlank(message = "Client`s last name must not be empty!") final String lastName, @NotNull final Account account, @NotNull final Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
        this.address = address;
    }

    public int getId() {
        return id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(final Account account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(final Set<Loan> loans) {
        this.loans = loans;
    }
}
