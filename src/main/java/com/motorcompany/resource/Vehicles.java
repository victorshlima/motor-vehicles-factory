package com.motorcompany.resource;

import com.motorcompany.dao.VehicleDao;
import com.motorcompany.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

//    @GetMapping("vehicle/{modelCode}")
//    @ApiOperation(value = "Get a specific a Vehicle Entity", response = VehicleModel.class)
//    @ResponseStatus(HttpStatus.OK)
//    public VehicleModel getVehicleById(@PathVariable("modelCode") int modelCode) {
//        return vehicleDao.findByModelCode(modelCode);
//    }

    @GetMapping("vehicle")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> getAllVehicle() {
        return vehicleDao.findAll();
    }


}
