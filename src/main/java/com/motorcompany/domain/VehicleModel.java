package com.motorcompany.domain;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motorcompany.enums.vehicle.Whells;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
public class VehicleModel  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private long id;
    @NotNull(message = "modelCode is a mandatory field")
    @Column(unique = true, nullable = true)
    private int modelCode;
    @Column
    private Whells numberWheels;
    @Column
    @Range(min = 125, max = 2000, message = "The range value is 125 - 2000")
    private short engineDisplacement;
    @Column
    private String commercialName;
    @Column
    private short numberPassengers;
    @JsonFormat(pattern = "yyyy")
    @Column(name = "modelYear")
    private Date modelYear;
    @Column
    private Boolean steppe;

    public int getModelCode() {
        return modelCode;
    }

    public void setModelCode(int modelCode) {
        this.modelCode = modelCode;
    }

    public Whells getNumberWheels() {
        return numberWheels;
    }

    public void setNumberWheels(Whells numberWheels) {
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

    public Date getModelYear() {
        return modelYear;
    }

    public void setModelYear(Date modelYear) {
        this.modelYear = modelYear;
    }

    public Boolean getSteppe() {
        return steppe;
    }

    public void setSteppe(Boolean steppe) {
        this.steppe = steppe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleModel that = (VehicleModel) o;
        return id == that.id &&
                modelCode == that.modelCode &&
                engineDisplacement == that.engineDisplacement &&
                numberPassengers == that.numberPassengers &&
                numberWheels == that.numberWheels &&
                Objects.equals(commercialName, that.commercialName) &&
                Objects.equals(modelYear, that.modelYear) &&
                Objects.equals(steppe, that.steppe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelCode, numberWheels, engineDisplacement, commercialName, numberPassengers, modelYear, steppe);
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