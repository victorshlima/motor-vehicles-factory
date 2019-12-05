package com.motorcompany.resource.rest;


import com.motorcompany.dao.FactoryDao;
import com.motorcompany.domain.Factory;

//import com.motorcompany.messaging.FactoryMessageConverter;
import com.motorcompany.messaging.FactoryMessageConverter;
import com.motorcompany.service.FactoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;

import java.util.Optional;

import static com.motorcompany.messaging.config.ActiveMQConfig.FACTORY_QUEUE;


//import static com.motorcompany.messaging.config.ConfigFatoryQueue.FACTORY_QUEUE;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import io.swagger.annotations.ApiOperation;


//@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class VehiclesFactory {


    private static final Logger LOGGER =   LoggerFactory.getLogger(FactoryMessageConverter.class);

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    private final FactoryDao factoryDao;
    private  FactoryServiceImpl factoryServiceImpl;
    public VehiclesFactory(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }

    @PostMapping(path = "/factory")
   // @ApiOperation(value = "Create a Vehicle Entity", response = Factory.class)
    public ResponseEntity<?> CreateVehicleEntity(@RequestBody Factory factory) {
        LOGGER.info("");
        factoryServiceImpl.FabricationProcess(factory);
        return new ResponseEntity<>(factoryDao.save(factory).getId(),HttpStatus.CREATED);
    }

}
