package com.motorcompany.service;


import com.motorcompany.dao.FactoryDao;
import com.motorcompany.domain.Factory;
import com.motorcompany.domain.VehicleModel;
import com.motorcompany.enums.vehicle.ExteriorCollor;
import com.motorcompany.enums.vehicle.InteriorType;
import com.motorcompany.enums.vehicle.PaintType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;
import javax.jms.Topic;

import static com.motorcompany.messaging.config.ActiveMQConfig.FACTORY_QUEUE;

@Service
@Transactional
public class FactoryServiceImpl   {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    FactoryDao factoryDao;

//    public FactoryServiceImpl() {
//        jmsTemplate.setMessageConverter( messageConverter());
//    }

    public ResponseEntity<?> FabricationProcess(Factory factory) {

        jmsTemplate.convertAndSend(FACTORY_QUEUE, factory);

        return new ResponseEntity<>(factoryDao.save(factory).getId(),HttpStatus.CREATED);
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }
}
