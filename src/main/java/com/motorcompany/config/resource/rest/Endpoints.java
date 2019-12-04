package com.motorcompany.config.resource.rest;


import com.motorcompany.config.dao.VehicleDao;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.motorcompany.config.dao.AgendaDao;
import com.motorcompany.config.dao.SessionDao;
import com.motorcompany.config.dao.VoteDao;
import com.motorcompany.config.domain.*;
import restvote.service.VotationService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class Endpoints {

    @Autowired
    private final VehicleDao vehicleDao;

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
    @ApiOperation(value = "Create a Vehicle Entity", response = Vehicle.class)
    public ResponseEntity<?> CreateVehicleEntity(@RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(vehicleDao.save(vehicle),HttpStatus.CREATED);
    }

    //PATCH causa erro no MOCK test
    @PutMapping(path = "vehicle/{modelCode}")
    @ApiOperation(value = "atualize a Vehicle Entity", response = String.class)
    public ResponseEntity<?> save(@PathVariable int modelCode, @RequestBody Vehicle vehicle) {
        return new ResponseEntity<>(vehicleDao.save(vehicle).getModelCode(),HttpStatus.CREATED);
    }

    @GetMapping("vehicle/{modelCode}")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle getVehicleById(@PathVariable("modelCode") int modelCode) {
        return vehicleDao.findByModelCode(modelCode);
    }

    @GetMapping("vehicle")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> getAllVehicle() {
        return vehicleDao.findAll();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "vehicle/{modelCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable int modelCode) {
         vehicleDao.deleteByModelCode(modelCode);
    }



}
