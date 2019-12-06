package com.motorcompany.messaging.listener;

import com.motorcompany.domain.Factory;
import com.motorcompany.messaging.config.GenericMessageConverter;
import com.motorcompany.service.ServiceProducer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
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
import javax.jms.Topic;
import java.io.IOException;

import static com.motorcompany.messaging.listener.PaintConsumer.PAINT_QUEUE;


@Component
public class FactoryConsumer {
    public static final String FACTORY_QUEUE = "factory.queue";
    public static final String REPLY_FACTORY_QUEUE = "reply.factory.queue";
    public static final String FACTORY_TOPIC = "factory.topic";
    private static Logger log = LoggerFactory.getLogger(FactoryConsumer.class);
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    ServiceProducer factoryServiceImpl = new ServiceProducer();
    @Autowired
    GenericMessageConverter factoryMessageConverter;
    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public Queue QueueFactory() {
        return new ActiveMQQueue(FACTORY_QUEUE);
    }

    @Bean
    public Queue QueueReplyFACTORY() {
        return new ActiveMQQueue(REPLY_FACTORY_QUEUE);
    }

    @Bean
    public Topic TopicFactory() {
        return new ActiveMQTopic(FACTORY_TOPIC);
    }

    @JmsListener(destination = FACTORY_QUEUE)
    public void consumer(Object factoryObject) throws IOException, JMSException {
        Factory factory = (Factory) factoryMessageConverter.JsonUnMarshaller(factoryObject, Factory.class);
        factoryServiceImpl.FabricationProcessSaveNewVehicle(factory);
        jmsTemplate.convertAndSend(PAINT_QUEUE, factory);
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