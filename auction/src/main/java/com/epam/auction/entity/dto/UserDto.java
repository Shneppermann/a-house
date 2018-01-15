package com.epam.auction.entity.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * User DTO object
 */

public class UserDto extends AbstractDto {

    private static final long serialVersionUID = -7457484593301666262L;
    private String login;
    private String name;
    private String surname;
    private BigDecimal balance;
    private String role;

    public UserDto() {
    }

    public UserDto(int id, String login, String name, String surname, BigDecimal balance, String role) {
        super(id);
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public UserDto clone() {
        return (UserDto) super.clone();
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
        UserDto userDto = (UserDto) object;
        return Objects.equals(login, userDto.login) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(balance, userDto.balance) &&
                Objects.equals(role, userDto.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), login, name, surname, balance, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", balance=" + balance +
                ", role='" + role + '\'' +
                "} " + super.toString();
    }
}
