package org.company.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int id;

    @NotNull
    @Column(name = "account_login")
    private String login;

    @NotNull
    @Column(name = "account_password")
    private String password;

    @NotNull
    @Column(name = "account_email_address")
    private String emailAddress;

    public Account() {
    }

    public Account(@NotNull final String login, @NotNull final String password, @NotNull final String emailAddress) {
        this.login = login;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
