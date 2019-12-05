package com.motorcompany.resource.rest;


import com.motorcompany.dao.FactoryDao;
import com.motorcompany.dao.VehicleDao;
import com.motorcompany.domain.Factory;
import com.motorcompany.domain.Vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import io.swagger.annotations.ApiOperation;

import javax.transaction.Transactional;
import java.util.List;

//@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class VehiclesFactory {

    @Autowired
    private final FactoryDao factoryDao;

    public VehiclesFactory(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }

    @PostMapping(path = "/factory")
//    @ApiOperation(value = "Create a Vehicle Entity", response = Factory.class)
    public ResponseEntity<?> CreateVehicleEntity(@RequestBody Factory factory) {
        return new ResponseEntity<>(factoryDao.save(factory).getId(),HttpStatus.CREATED);
    }


}
