package com.motorcompany.service;


import com.motorcompany.dao.FactoryDao;
import com.motorcompany.dao.VehicleDao;
import com.motorcompany.dao.VehicleModelDao;
import com.motorcompany.domain.Factory;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.domain.Vehicle;
import com.motorcompany.enums.vehicle.ExteriorCollor;
import com.motorcompany.enums.vehicle.InteriorType;
import com.motorcompany.enums.vehicle.PaintType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.motorcompany.messaging.listener.FactoryConsumer.FACTORY_QUEUE;

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

    Vehicle vehiculo;
    VehicleModel vehicleModel;

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    public ServiceProducer() {
        this.vehiculo = new Vehicle();
        JmsTemplate jmsTemplate = new JmsTemplate ();
        jmsTemplate.setMessageConverter(messageConverter());
    }

    public  void FabricationProcess(Factory factory) {
        jmsTemplate.setMessageConverter(messageConverter());
        jmsTemplate.convertAndSend(FACTORY_QUEUE, factory);

    }

    public  void FabricationProcessSaveNewVehicle(Factory factory) {
        vehicleModel =  vehicleModelDao.findByModelCode(factory.getModelCode());
        vehiculo.setVehicleModel(vehicleModel);
        Calendar cal = GregorianCalendar.getInstance();
      //  vehiculo.setModelYear(cal.get(Calendar.YEAR));
        vehicleDao.save(vehiculo);
    }

    public  void FabricationProcessPaintVehicle(ExteriorCollor exteriorCollor,PaintType paintType) {
        vehiculo.setPaintType(paintType);
        vehiculo.setExteriorCollor(exteriorCollor);
        vehicleDao.save(vehiculo);
    }

    public  void FabricationProcessInteriorType(InteriorType interiorType) {
        vehiculo.setInteriorType(interiorType);
        vehicleDao.save(vehiculo);
    }
    public long factorySave(Factory factory) {
        return factoryDao.save(factory).getId();
    }



    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

}
