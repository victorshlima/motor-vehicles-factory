package com.motorcompany.resource;

import com.motorcompany.dao.VehicleModelDao;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.motorcompany.domain.*;
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
    @PostMapping(path = "/vehicle")
    @ApiOperation(value = "Create a Vehicle Entity", response = VehicleModels.class)
    public ResponseEntity<?> CreateVehicleEntity(@RequestBody VehicleModel vehicleModel) {
        return new ResponseEntity<>(vehicleModelDao.save(vehicleModel),HttpStatus.CREATED);
    }

    //PATCH causa erro no MOCK test
    @PutMapping(path = "vehicle/{modelCode}")
    @ApiOperation(value = "atualize a Vehicle Entity", response = Integer.class)
    public ResponseEntity<?> save(@PathVariable int modelCode, @RequestBody VehicleModel vehicleModel) {
        return new ResponseEntity<>(vehicleModelDao.save(vehicleModel).getModelCode(),HttpStatus.CREATED);
    }

    @GetMapping("vehicle/{modelCode}")
    @ApiOperation(value = "Get a specific a Vehicle Entity", response = VehicleModel.class)
    @ResponseStatus(HttpStatus.OK)
    public VehicleModel getVehicleById(@PathVariable("modelCode") int modelCode) {
        return vehicleModelDao.findByModelCode(modelCode);
    }

    @GetMapping("vehicle")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public List<VehicleModel> getAllVehicle() {
        return vehicleModelDao.findAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "vehicle/{modelCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int modelCode) {

        vehicleModelDao.deleteByModelCode(modelCode);
    }

}
