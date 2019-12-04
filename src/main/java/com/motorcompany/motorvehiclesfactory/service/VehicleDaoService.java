package com.motorcompany.motorvehiclesfactory.service;


import com.motorcompany.motorvehiclesfactory.dao.VehicleDao;
import com.motorcompany.motorvehiclesfactory.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@EnableAutoConfiguration
@Service
public class VehicleDaoService {

    @Autowired
    VehicleDao vehicleDao;


//    public Vehicle CreateVehicle(Vehicle vehicle)
//    {
//
//        vehicleDao.save(vehicle);
//        return vehicle;
//    }

}
