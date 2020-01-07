package com.motorcompany.service;


import com.motorcompany.Exception.NotExistDaoException;
import com.motorcompany.dao.FactoryDao;
import com.motorcompany.dao.VehicleDao;
import com.motorcompany.dao.VehicleModelDao;
import com.motorcompany.domain.Factory;
import com.motorcompany.domain.Vehicle;
import com.motorcompany.domain.VehicleModel;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

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
    Factory factory;

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    public ServiceProducer() {
        this.vehiculo = new Vehicle();
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setMessageConverter(messageConverter());
    }
    public void FabricationProcess(Factory factory) {
        jmsTemplate.setMessageConverter(messageConverter());
        jmsTemplate.convertAndSend(FACTORY_QUEUE, factory);
    }

    public VehicleModel UpdateModel(int modelId,VehicleModel vehicleModel ) {
        VehicleModel  vehicleModelTmp = new  VehicleModel();
        try {          
            vehicleModelTmp = vehicleModelDao.findByModelCode(modelId);
            vehicleModelTmp = vehicleModel;
            vehicleModelDao.save(vehicleModelTmp);
            return vehicleModelTmp;
            // vehicleDao.save(vehiculo);
          //  fabricationStatus(factory, "Model Structure Created, step set paint");
        }catch (Exception e){
         //   fabricationStatus(factory, "problem on create basic Structure \n exception:"
             //       + e.getLocalizedMessage());
            return vehicleModelTmp;
        }

    }
    public void deleteVehicleModel(int modelId ) {

        vehicleModel = getVehicleModelByCode(modelId);
        if(!vehicleModel.equals(null)) {
            vehicleModelDao.deleteByModelCode(modelId);
        }else{
            throw new NotExistDaoException("deleteByModelCode " + modelId);
        }
    }



    public void CreateVehicle(Factory factory) {
        try {
            vehicleModel = getVehicleModelByCode(factory.getModelCode());
            vehiculo.setVehicleModel(vehicleModel);
            Calendar cal = GregorianCalendar.getInstance();
            vehiculo.setBuildYear(cal.get(Calendar.YEAR));
            vehicleDao.save(vehiculo);
            fabricationStatus(factory, "Model Structure Created, step set paint");
        }catch (Exception e){
            fabricationStatus(factory, "problem on create basic Structure \n exception:"
                    + e.getLocalizedMessage());
        }
    }



    public void SetPaintVehicle(Factory factory) {
     try {
        vehiculo.setPaintType(factory.getPaintType());
        vehiculo.setExteriorCollor(factory.getExteriorCollor());
        vehicleDao.save(vehiculo);
         fabricationStatus(factory, "Vehicle painted, next step set InteriorType");
    }catch (Exception e){
        fabricationStatus(factory,   "problem on set InteriorType \n exception: "
                +e.getLocalizedMessage());
    }
 }
    public void SetInteriorType(Factory factory) {
        try {
       fabricationStatus(factory, "last Step initialized ");
        vehiculo.setInteriorType(factory.getInteriorType());
        vehicleDao.save(vehiculo);
            fabricationStatus(factory, "DONE!");
        }catch (Exception e){
            fabricationStatus(factory, "problem on set InteriorType \n exception: "
                    + e.getLocalizedMessage());
        }
    }
    public long factorySave(Factory factory) {
        return factoryDao.save(factory).getId();
    }

    public String factoryGetStatus(long id)
    {
       try {
           Optional<Factory> factoryOptional  = factoryDao.findById(id);
           this.factory =factoryOptional.get();
           return  this.factory.getStatus();
       }catch(Exception e){
            throw new NotExistDaoException("Id not localized");
       }
    }

    public void fabricationStatus(Factory factory, String status ) {
        factory.setStatus(status);
         factoryDao.save(factory);
    }

    public VehicleModel getVehicleModelByCode(int modelCode) {
        Optional<VehicleModel> vehicleOptional = Optional.ofNullable(vehicleModelDao.findByModelCode(modelCode));
        validateVehiclePresence(vehicleOptional);
        return vehicleOptional.get();
    }

    private void validateVehiclePresence(Optional<VehicleModel> objectOptional) {
        if (!objectOptional.isPresent()) {
            throw new NotExistDaoException("Error agenda not found");
        }
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }
}
