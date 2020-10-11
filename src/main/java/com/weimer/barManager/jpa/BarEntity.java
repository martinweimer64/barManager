package com.weimer.barManager.jpa;

import com.google.gson.Gson;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "BAR")
public class BarEntity {

    @Id
    @Column(name = "CUIT")
    private int cuit;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SCORE")
    private int score;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "HOUR")
    private String attentionHours;

    @Column(name = "PARKING")
    private boolean parking;

    public BarEntity() {
        //POJO JAVA OBJECT
    }

    public BarEntity(int cuit, String name, int score, String address, String phone, String email,
                     String attentionHours, boolean parking) {
        this.cuit = cuit;
        this.name = name;
        this.score = score;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.attentionHours = attentionHours;
        this.parking = parking;
    }

    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAttentionHours() {
        return attentionHours;
    }

    public void setAttentionHours(String attentionHours) {
        this.attentionHours = attentionHours;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BarEntity barEntity = (BarEntity) o;
        return cuit == barEntity.cuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cuit);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

