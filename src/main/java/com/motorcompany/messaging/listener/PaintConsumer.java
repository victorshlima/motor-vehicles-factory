package com.motorcompany.messaging.listener;

import com.motorcompany.domain.Factory;
import com.motorcompany.messaging.config.GenericMessageConverter;
import com.motorcompany.service.ServiceProducer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;
import java.io.IOException;

import static com.motorcompany.messaging.listener.InteriorConsumer.INTERIOR_TYPE_QUEUE;

@Component
public class PaintConsumer {
    public static final String PAINT_QUEUE = "paint.queue";
    public static final String PAINT_TOPIC = "paint.topic";
    private static Logger log = LoggerFactory.getLogger(PaintConsumer.class);
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    ServiceProducer serviceProducer = new ServiceProducer();
    @Autowired
    GenericMessageConverter factoryMessageConverter;
    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public Queue QueueFACTORY() {
        return new ActiveMQQueue(PAINT_QUEUE);
    }

    @JmsListener(destination = PAINT_QUEUE)
    public void consumer(Object factoryObject) throws IOException, JMSException {
        Factory factory = (Factory) factoryMessageConverter.JsonUnMarshaller(factoryObject, Factory.class);
        serviceProducer.SetPaintVehicle(factory);
        jmsTemplate.convertAndSend(INTERIOR_TYPE_QUEUE, factory);
    }
    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }
    @Bean
    public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        return factory;
    }
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setTrustAllPackages(true);
        factory.setBrokerURL(brokerUrl);
        return factory;
    }
}