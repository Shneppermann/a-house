package com.epam.auction.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * User entity
 */
public class User extends Entity {

    private static final long serialVersionUID = 2436270208482845645L;
    private int idRole;
    private String login;
    private String password;
    private String name;
    private String surname;
    private BigDecimal balance;

    public User() {
    }

    public User(int idRole, String login, String password, String name, String surname, BigDecimal balance) {
        this.idRole = idRole;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public User clone() {
        return (User) super.clone();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        User user = (User) object;
        return idRole == user.idRole &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), idRole, login, password, name, surname, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "idRole=" + idRole +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", balance=" + balance +
                "} " + super.toString();
    }
}
