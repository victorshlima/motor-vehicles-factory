package com.motorcompany.service;


import com.motorcompany.dao.FactoryDao;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.enums.vehicle.ExteriorCollor;
import com.motorcompany.enums.vehicle.InteriorType;
import com.motorcompany.enums.vehicle.PaintType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import javax.jms.Topic;

@Service
@Transactional
public class FactoryServiceImpl   {


    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    @Autowired
    Topic topic;


    public void CreateVehicleFactory (FactoryDao FactoryDao){



    }


    String CreateVehicleStructureWithAttributes (VehicleModel vehicleModel){
    return "VehicleModel";}

    String PaintNewVehicle (long vehicleId, ExteriorCollor exteriorCollor){
        return "paintNewVehicle";}

    String RevestNewVehicle (long vehicleId, InteriorType interiorType){
        return "RevestNewVehicle";
     }


}
