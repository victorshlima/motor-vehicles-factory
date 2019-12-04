package com.motorcompany.config.domain;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motorcompany.config.enums.VehicleWheels;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
public class Vehicle extends AbstractEntity {

    @NotNull(message = "modelCode is a mandatory field")
    @Column(unique = true, nullable = true)
    private int modelCode;

    @Column(nullable = true)
    private VehicleWheels numberWheels;

    @Column(nullable = true)
    private short engineDisplacement;

    @Column(nullable = true)
    private String commercialName;

    @Column(nullable = true)
    private short numberPassengers;

    @JsonFormat(pattern = "yyyy")
    @Column(name = "modelYear")
    private short modelYear;

    @Column(nullable = true)
    private Boolean steppe;

    public int getModelCode() {
        return modelCode;
    }

    public void setModelCode(int modelCode) {
        this.modelCode = modelCode;
    }

    public VehicleWheels getNumberWheels() {
        return numberWheels;
    }

    public void setNumberWheels(VehicleWheels numberWheels) {
        this.numberWheels = numberWheels;
    }

    public short getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(short engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public short getNumberPassengers() {
        return numberPassengers;
    }

    public void setNumberPassengers(short numberPassengers) {
        this.numberPassengers = numberPassengers;
    }

    public short getModelYear() {
        return modelYear;
    }

    public void setModelYear(short modelYear) {
        this.modelYear = modelYear;
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
                "modelCode=" + modelCode +
                ", numberWheels=" + numberWheels +
                ", engineDisplacement=" + engineDisplacement +
                ", commercialName='" + commercialName + '\'' +
                ", numberPassengers=" + numberPassengers +
                ", modelYear=" + modelYear +
                ", steppe=" + steppe +
                '}';
    }
}
