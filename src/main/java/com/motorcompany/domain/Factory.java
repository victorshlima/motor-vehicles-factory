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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect
@Entity
public class Factory extends AbstractEntity {

    @NotNull(message = "modelCode is a mandatory field")
    @Column(nullable = true)
    private int modelCode;

    @Column(nullable = true)
    private ExteriorCollor exteriorCollor;

    @Column(nullable = true)
    private PaintType paintType;

    @Column(nullable = true)
    private InteriorType interiorType;

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

    public PaintType getPaintType() {
        return paintType;
    }

    public void setPaintType(PaintType paintType) {
        paintType = paintType;
    }

    public InteriorType getInteriorType() {
        return interiorType;
    }

    public void setInteriorType(InteriorType interiorType) {
        this.interiorType = interiorType;
    }
}
