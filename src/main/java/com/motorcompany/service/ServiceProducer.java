package com.motorcompany.service;


import com.motorcompany.dao.FactoryDao;
import com.motorcompany.dao.VehicleDao;
import com.motorcompany.dao.VehicleModelDao;
import com.motorcompany.domain.Factory;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.domain.Vehiculo;
import com.motorcompany.enums.vehicle.ExteriorCollor;
import com.motorcompany.enums.vehicle.InteriorType;
import com.motorcompany.enums.vehicle.PaintType;
import com.motorcompany.messaging.FactoryMessageConverter;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;

import java.io.IOException;
import java.util.Date;

import static com.motorcompany.messaging.listener.ProducerConfigQueue.FACTORY_QUEUE;


@Service
@Transactional
public class ServiceProducer {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    ServiceProducer factoryServiceImpl;
    @Autowired
    FactoryDao factoryDao;
    @Autowired
    VehicleDao vehicleDao;
    @Autowired
    VehicleModelDao vehicleModelDao;

    Vehiculo vehiculo;
    VehicleModel vehicleModel;

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    public ServiceProducer() {
        JmsTemplate jmsTemplate = new JmsTemplate ();
        jmsTemplate.setMessageConverter(messageConverter());
    }
//
    public  void FabricationProcess(Factory factory) {
        jmsTemplate.convertAndSend(FACTORY_QUEUE, factory);
    }
//
    public  void FabricationProcessStesp1(Factory factory) {
        vehicleModel =  vehicleModelDao.findByModelCode(factory.getModelCode());
        vehiculo.setVehicleModel(vehicleModel);
     //   vehiculo.setModelYear(new Date().getYear());
        vehicleDao.save(vehiculo);
    }

    public  void FabricationProcessStesp2(ExteriorCollor exteriorCollor) {
        vehiculo.setExteriorCollor(exteriorCollor);
        vehicleDao.save(vehiculo);
    }

    public  void FabricationProcessStesp3(InteriorType interiorType) {
        vehiculo.setInteriorType(interiorType);
        vehicleDao.save(vehiculo);
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }


}
