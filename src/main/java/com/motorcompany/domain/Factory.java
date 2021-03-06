package com.motorcompany.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motorcompany.enums.vehicle.ExteriorCollor;
import com.motorcompany.enums.vehicle.InteriorType;
import com.motorcompany.enums.vehicle.PaintType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
public class Factory extends AbstractEntity implements Serializable {

    @NotNull(message = "modelCode is a mandatory field")
    @Column(nullable = true)
    private int modelCode;

    @Column(nullable = true)
    private ExteriorCollor exteriorCollor;

    @Column(nullable = true)
    private PaintType paintType;

    @Column(nullable = true)
    private InteriorType interiorType;
    @Column
    private String status;

    public Factory() {

    }

    public Factory(@NotNull(message = "modelCode is a mandatory field") int modelCode, ExteriorCollor exteriorCollor, PaintType paintType, InteriorType interiorType) {
        this.modelCode = modelCode;
        this.exteriorCollor = exteriorCollor;
        this.paintType = paintType;
        this.interiorType = interiorType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getModelCode() {
        return modelCode;
    }

    public void setModelCode(int modelCode) {
        this.modelCode = modelCode;
    }

    public ExteriorCollor getExteriorCollor() {
        return exteriorCollor;
    }

    public void setExteriorCollor(ExteriorCollor exteriorCollor) {
        this.exteriorCollor = exteriorCollor;
    }

    public InteriorType getInteriorType() {
        return interiorType;
    }

    public void setInteriorType(InteriorType interiorType) {
        this.interiorType = interiorType;
    }

    public PaintType getPaintType() {
        return paintType;
    }

    public void setPaintType(PaintType paintType) {
        this.paintType = paintType;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "modelCode=" + modelCode +
                ", exteriorCollor=" + exteriorCollor +
                ", paintType=" + paintType +
                ", interiorType=" + interiorType +
                '}';
    }
}
