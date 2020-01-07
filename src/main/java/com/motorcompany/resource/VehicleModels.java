package com.motorcompany.resource;

import com.motorcompany.dao.VehicleModelDao;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.service.ServiceProducer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.transaction.Transactional;
import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class VehicleModels {

    @Autowired
    private final VehicleModelDao vehicleModelDao;
    public VehicleModels(VehicleModelDao vehicleDao) {
        this.vehicleModelDao = vehicleDao;
    }


    @Autowired
    ServiceProducer serviceProducer;

    @PostMapping(path = "/vehicleModels")
    @ApiOperation(value = "Create a Create a Vehicle Model Entity", response = VehicleModels.class)
    public ResponseEntity<?> CreateVehicleEntity(@RequestBody VehicleModel vehicleModel) {
        return new ResponseEntity<>(vehicleModelDao.save(vehicleModel), HttpStatus.CREATED);
    }

    @PutMapping (path = "vehicleModels/{modelCode}")
    @ApiOperation(value = "atualize a Create a Vehicle Model Entity", response = Integer.class)
    public ResponseEntity<?> put(@PathVariable int modelCode, @RequestBody VehicleModel vehicleModel) {
        VehicleModel tempVehicleModel  =   vehicleModelDao.findByModelCode(modelCode);
        tempVehicleModel = vehicleModel;
        return new ResponseEntity<>(vehicleModelDao.save(tempVehicleModel), HttpStatus.CREATED);
    }
    //PATCH causa erro no MOCK test
    @PatchMapping (path = "vehicleModels/{modelCode}")
    @ApiOperation(value = "atualize a Create a Vehicle Model Entity", response = Integer.class)
    public ResponseEntity<?> save(@PathVariable int modelCode, @RequestBody VehicleModel vehicleModel) {
        return new ResponseEntity<>(serviceProducer.UpdateModel( modelCode, vehicleModel), HttpStatus.CREATED);
    }

    @GetMapping("vehicleModels/{modelCode}")
    @ApiOperation(value = "Get a specific a Create a Vehicle Model Entity", response = VehicleModel.class)
    public VehicleModel getVehicleById(@PathVariable("modelCode") int modelCode) {
        return vehicleModelDao.findByModelCode(modelCode);
    }

    @ApiOperation(value = "Get all Vehicle Model Entity", response = VehicleModel.class)
    @GetMapping("vehicleModels")
    public List<VehicleModel> getAllVehicle() {
        return vehicleModelDao.findAll();
    }


    @DeleteMapping("vehicleModels/{modelCode}")
  //  @RequestMapping(method = RequestMethod.DELETE, value = "vehicleModels/{modelCode}", produces = MediaType.APPLICATION_JSON_VALUE)
     public void delete(@PathVariable int modelCode) {
        serviceProducer.deleteVehicleModel(modelCode);
    }

}
