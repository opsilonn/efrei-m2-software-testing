package com.stproject.prototype.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity(name="user")
public class User {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int idUser;
    private String civility;
    private String nameFirst;
    private String nameLast;
    private String address;
    private String zipCode;
    private String city;
    private Date date_creation;

    public User() {
    }

    public User(String civility, String nameFirst, String nameLast, String address, String zipCode, String city, Date date_creation) {
        this.civility = civility;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.date_creation = date_creation;
    }

    public User(int idUser, String civility, String nameFirst, String nameLast, String address, String zipCode, String city, Date date_creation) {
        this.idUser = idUser;
        this.civility = civility;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.date_creation = date_creation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", civility='" + civility + '\'' +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", date_creation=" + date_creation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getIdUser() == user.getIdUser() &&
                Objects.equals(getCivility(), user.getCivility()) &&
                Objects.equals(getNameFirst(), user.getNameFirst()) &&
                Objects.equals(getNameLast(), user.getNameLast()) &&
                Objects.equals(getAddress(), user.getAddress()) &&
                Objects.equals(getZipCode(), user.getZipCode()) &&
                Objects.equals(getCity(), user.getCity()) &&
                Objects.equals(getDate_creation().toString(), user.getDate_creation().toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUser(), getCivility(), getNameFirst(), getNameLast(), getAddress(), getZipCode(), getCity(), getDate_creation());
    }
}
