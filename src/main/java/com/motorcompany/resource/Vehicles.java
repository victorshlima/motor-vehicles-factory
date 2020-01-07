package com.motorcompany.resource;

import com.motorcompany.dao.VehicleDao;
import com.motorcompany.domain.Vehicle;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.transaction.Transactional;
import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class Vehicles {

    @Autowired
    private final VehicleDao vehicleDao;

    public Vehicles(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

//    @ApiOperation(value = "Get a specific a Vehicle Entity", response = Vehicle.class)//    @GetMapping("vehicle/{modelCode}")

//    @ResponseStatus(HttpStatus.OK)
//    public Vehicle getVehicleById(@PathVariable("modelCode") int modelCode) {
//        return vehicleDao.(modelCode);
//    }


    @ApiOperation(value="Get All Vehicles", response = Vehicle.class)
    @GetMapping("vehicle")
    public List<Vehicle> getAllVehicle() {
        return vehicleDao.findAll();
    }


}
