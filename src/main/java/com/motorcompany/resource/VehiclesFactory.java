package com.motorcompany.resource;


import com.motorcompany.dao.FactoryDao;
import com.motorcompany.domain.Factory;
import com.motorcompany.messaging.config.GenericMessageConverter;
import com.motorcompany.service.ServiceProducer;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class VehiclesFactory {

    private static final Logger LOGGER =   LoggerFactory.getLogger(GenericMessageConverter.class);

    @Autowired
    private final FactoryDao factoryDao;
    @Autowired
    private ServiceProducer factoryServiceImpl;
    public VehiclesFactory(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }

    @PostMapping(path = "/factory")
    @ApiOperation(value = "Create a Vehicle Entity", response = Factory.class)
    public ResponseEntity<?> CreateVehicleEntity(@RequestBody Factory factory) {
        LOGGER.info("");
        factoryServiceImpl.FabricationProcess(factory);
        return new ResponseEntity<>(factoryDao.save(factory).getId(),HttpStatus.CREATED);
    }
}
