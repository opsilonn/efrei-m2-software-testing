package com.stproject.prototype.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity(name="contract")
public class Contract {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int idContract;

    private float price;
    private Date date;
    private float electricalConsumption;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_idUser")
    private User user_idUser;

    public Contract() {
    }

    public Contract(int id, float price, Date date, float electricalConsumption) {
        this.idContract = id;
        this.price = price;
        this.date = date;
        this.electricalConsumption = electricalConsumption;
    }

    public Contract(float price, Date date, float electricalConsumption, User user_idUser) {
        this.price = price;
        this.date = date;
        this.electricalConsumption = electricalConsumption;
        this.user_idUser = user_idUser;
    }

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getElectricalConsumption() {
        return electricalConsumption;
    }

    public void setElectricalConsumption(float electricalConsumption) {
        this.electricalConsumption = electricalConsumption;
    }

    public User getUser_idUser() {
        return user_idUser;
    }

    public void setUser_idUser(User user_idUser) {
        this.user_idUser = user_idUser;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "idContract=" + idContract +
                ", price=" + price +
                ", date=" + date +
                ", electricalConsumption=" + electricalConsumption +
                ", user_idUser=" + user_idUser +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract contract = (Contract) o;
        return getIdContract() == contract.getIdContract() &&
                Float.compare(contract.getPrice(), getPrice()) == 0 &&
                Float.compare(contract.getElectricalConsumption(), getElectricalConsumption()) == 0 &&
                getDate().toString().equals(contract.getDate().toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdContract(), getPrice(), getDate(), getElectricalConsumption());
    }
}
