package com.motorcompany.resource;


import com.motorcompany.dao.VehicleModelDao;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.messaging.config.GenericMessageConverter;
import com.motorcompany.service.ServiceProducer;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class Vehicle {
    private static final Logger LOGGER =   LoggerFactory.getLogger(GenericMessageConverter.class);
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    private  VehicleModelDao vehicleModelDao;
    private ServiceProducer factoryServiceImpl;
    public Vehicle(VehicleModelDao vehicleModelDao) {
        this.vehicleModelDao = vehicleModelDao;
    }

    @PostMapping(path = "/vehicleModels")
    @ApiOperation(value = "Create a Vehicle Entity", response = VehicleModel.class)
    public ResponseEntity<?> CreateVehicleModelEntity(@RequestBody VehicleModel vehicleModel) {
        LOGGER.info("inbound json='{}'", vehicleModel.toString());
        return new ResponseEntity<>(vehicleModelDao.save(vehicleModel).getModelCode(), HttpStatus.CREATED);
    }

}
