package com.motorcompany.motorvehiclesfactory.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Vehicle extends AbstractEntity {

    @Id // remover o getter para ficar invisivel
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
 //   @GeneratedValue(strategy = GenerationType.AUTO)
    private int model_code;

    public void setModel_code(int model_code) {
        this.model_code = model_code;
    }

    @Column
    private short number_wheels;

    @Column
    private short engine_Displacement;

    @Column
    private String commercial_name;

    @Column
    private short number_passengers;

    @Column
    private short model_year;

    @Column
    private Boolean steppe;

    public int getModel_code() {
        return model_code;
    }


    public short getNumber_wheels() {
        return number_wheels;
    }

    public void setNumber_wheels(short number_wheels) {
        this.number_wheels = number_wheels;
    }

    public short getEngine_Displacement() {
        return engine_Displacement;
    }

    public void setEngine_Displacement(short engine_Displacement) {
        this.engine_Displacement = engine_Displacement;
    }

    public String getCommercial_name() {
        return commercial_name;
    }

    public void setCommercial_name(String commercial_name) {
        this.commercial_name = commercial_name;
    }

    public short getNumber_passengers() {
        return number_passengers;
    }

    public void setNumber_passengers(short number_passengers) {
        this.number_passengers = number_passengers;
    }

    public short getModel_year() {
        return model_year;
    }

    public void setModel_year(short model_year) {
        this.model_year = model_year;
    }

    public Boolean getSteppe() {
        return steppe;
    }

    public void setSteppe(Boolean steppe) {
        this.steppe = steppe;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ", number_wheels=" + number_wheels +
                ", engine_Displacement=" + engine_Displacement +
                ", commercial_name='" + commercial_name + '\'' +
                ", number_passengers=" + number_passengers +
                ", model_year=" + model_year +
                ", steppe=" + steppe +
                '}';
    }
}
