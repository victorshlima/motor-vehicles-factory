package com.motorcompany.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.motorcompany.enums.vehicle.ExteriorCollor;
import com.motorcompany.enums.vehicle.InteriorType;
import com.motorcompany.enums.vehicle.PaintType;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "vehicle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicleModel")
    private VehicleModel vehicleModel;
    @Column
    private ExteriorCollor exteriorCollor;
    @Column
    private PaintType paintType;
    @Column
    private InteriorType interiorType;
    @Column
    private int buildYear;

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public ExteriorCollor getExteriorCollor() {
        return exteriorCollor;
    }

    public void setExteriorCollor(ExteriorCollor exteriorCollor) {
        this.exteriorCollor = exteriorCollor;
    }

    public PaintType getPaintType() {
        return paintType;
    }

    public void setPaintType(PaintType paintType) {
        this.paintType = paintType;
    }

    public InteriorType getInteriorType() {
        return interiorType;
    }

    public void setInteriorType(InteriorType interiorType) {
        this.interiorType = interiorType;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleModel=" + vehicleModel +
                ", exteriorCollor=" + exteriorCollor +
                ", paintType=" + paintType +
                ", interiorType=" + interiorType +
                ", BuildYear=" + buildYear +
                '}';
    }
}
