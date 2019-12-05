package com.motorcompany.resource.rest;


import com.motorcompany.dao.VehicleModelDao;
import com.motorcompany.domain.Factory;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.messaging.config.FactoryMessageConverter;
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

//import com.motorcompany.messaging.config.FactoryMessageConverter;
 //import static com.motorcompany.messaging.config.ConfigFatoryQueue.FACTORY_QUEUE;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import io.swagger.annotations.ApiOperation;


@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class VehicleModels {
    private static final Logger LOGGER =   LoggerFactory.getLogger(FactoryMessageConverter.class);
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    private  VehicleModelDao vehicleModelDao;
    private ServiceProducer factoryServiceImpl;
    public VehicleModels(VehicleModelDao vehicleModelDao) {
        this.vehicleModelDao = vehicleModelDao;
    }

    @PostMapping(path = "/vehicleModels")
    @ApiOperation(value = "Create a Vehicle Entity", response = VehicleModel.class)
    public ResponseEntity<?> CreateVehicleModelEntity(@RequestBody VehicleModel vehicleModel) {
        LOGGER.info("inbound json='{}'", vehicleModel.toString());
        return new ResponseEntity<>(vehicleModelDao.save(vehicleModel).getModelCode(), HttpStatus.CREATED);
    }

}
