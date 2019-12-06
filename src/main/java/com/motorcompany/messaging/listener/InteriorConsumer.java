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


@Component
public class InteriorConsumer {
    private static Logger log = LoggerFactory.getLogger(InteriorConsumer.class);
    public static final String INTERIOR_TYPE_QUEUE = "interior.queue";
    public static final String INTERIOR_TYPE_TOPIC= "interior.topic";
    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public Queue QueueFACTORY() {
        return new ActiveMQQueue(INTERIOR_TYPE_QUEUE);
    }
    @Bean
    public Topic TopicFACTORY() {
        return new ActiveMQTopic(INTERIOR_TYPE_TOPIC);
    }

    @Autowired
    ServiceProducer factoryServiceImpl  = new ServiceProducer();
    @Autowired
    GenericMessageConverter factoryMessageConverter;

     @JmsListener(destination = INTERIOR_TYPE_QUEUE)
    public void consumer(Object factoryObject) throws IOException, JMSException {
         Factory factory = (Factory) factoryMessageConverter.JsonUnMarshaller(factoryObject, Factory.class );
         factoryServiceImpl.FabricationProcessStesp3(factory.getInteriorType());
      }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setMessageConverter( messageConverter() );
        return new JmsTemplate(activeMQConnectionFactory());
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