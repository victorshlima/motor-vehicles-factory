package com.motorcompany.motorvehiclesfactory.rest;

import com.motorcompany.motorvehiclesfactory.dao.VehicleDao;
import com.motorcompany.motorvehiclesfactory.model.Vehicle;
import com.motorcompany.motorvehiclesfactory.service.VehicleDaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("v1")
public class Endpoints {

    @Autowired
    VehicleDaoService vehicleDaoService;

    private final  VehicleDao vehicleDao;

    @Autowired
    public Endpoints(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @GetMapping("/status")
    @ApiOperation(value = "Return the Status of Application", response = String.class)
    @ResponseStatus(HttpStatus.OK)
    //verificar db
    public String StatusApplication() {
        return "Online";
    }


    @PostMapping(path = "/vehicle")
    @ApiOperation(value = "Create a Vehicle Entity", response = String.class)
    @Transactional()
    public ResponseEntity<?> CreateVehicleEntity(@Valid @RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(vehicleDao.save(vehicle),HttpStatus.CREATED);
    }

//    @PutMapping(path = "/vehicle")
//    @ApiOperation(value = "atualize a Vehicle Entity", response = String.class)
//    @Transactional()
//    public ResponseEntity<?> save(@Valid @RequestBody Vehicle vehicle) {
//        return new ResponseEntity<>(vehicleDao.save(vehicle).getModel_code(),HttpStatus.CREATED);
//    }

//    @DeleteMapping (path = "/vehicle/{model_code}")
//    @ApiOperation(value = "Delete a Vehicle Entity", response = String.class)
//    @Transactional()
//    public ResponseEntity<?> deleteVehicleEntity(@PathVariable("model_code") int model_code ) {
//        return new ResponseEntity<>(vehicleDao.deleteByModel_code(model_code),HttpStatus.CREATED);
//    }

}
