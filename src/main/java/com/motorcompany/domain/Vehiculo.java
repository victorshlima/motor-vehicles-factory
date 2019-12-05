package com.motorcompany.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.motorcompany.enums.vehicle.ExteriorCollor;
import com.motorcompany.enums.vehicle.InteriorType;
import com.motorcompany.enums.vehicle.PaintType;
import org.springframework.ui.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
    @Table(name = "vehiculo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public class Vehiculo extends AbstractEntity  {

    @JsonFormat(pattern = "yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modelYear")
    private Date modelYear;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicleModel")
    private VehicleModel vehicleModel;

    @Column(nullable = true)
    private ExteriorCollor exteriorCollor;

    @Column(nullable = true)
    private PaintType paintType;

    @Column(nullable = true)
    private InteriorType interiorType;

    public Date getModelYear() {
        return modelYear;
    }

    public void setModelYear(Date modelYear) {
        this.modelYear = modelYear;
    }
}
