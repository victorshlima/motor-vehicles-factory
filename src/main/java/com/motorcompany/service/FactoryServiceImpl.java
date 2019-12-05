//package com.motorcompany.service;
//
//
//import com.motorcompany.dao.FactoryDao;
//import com.motorcompany.domain.VehicleAttributes;
//import com.motorcompany.enums.vehicle.ExteriorCollor;
//import com.motorcompany.enums.vehicle.InteriorType;
//import com.motorcompany.enums.vehicle.PaintType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class FactoryServiceImpl   {
//
//    @Autowired
//    private final FactoryDao factoryDao;
//     public FactoryServiceImpl(FactoryDao factoryDao) {
//        this.factoryDao = factoryDao;
//    }
//
//
//    public long CreateVehicleFactory (VehicleAttributes vehicleAttributes){
//
//        return factoryDao.save(vehicleAttributes).getId();
//    }
//
//
//    String CreateVehicleStructureWithAttributes (VehicleAttributes vehicleAttributes){
//
//
//    return "VehicleAttributes";}
//
//    String PaintNewVehicle (long vehicleId, ExteriorCollor exteriorCollor){
//
//
//        return "paintNewVehicle";}
//
//    String RevestNewVehicle (long vehicleId, InteriorType interiorType){
//
//        return "RevestNewVehicle";
//     }
//
//
//}
