package com.motorcompany.messaging.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.motorcompany.messaging.FactoryMessageConverter;

import jdk.internal.instrumentation.Tracer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
//import com.motorcompany.messaging.JsonMessageConverter;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;


@EnableJms
@Configuration
public class ActiveMQConfig {
    public static final String STATUS_QUEUE = "status.queue";
    public static final String STATUS_TOPIC = "status.topic";
    public static final String FACTORY_QUEUE = "factory.queue";
    public static final String FACTORY_TOPIC = "factory.topic";

    @Value("${activemq.broker-url}")
    private String brokerUrl;

  //  @Primary
    @Bean
    public Queue QueueFACTORY() {
        return new ActiveMQQueue(FACTORY_QUEUE);
    }

  //  @Primary
    @Bean
    public Topic TopicFACTORY() {
        return new ActiveMQTopic(FACTORY_TOPIC);
    }

  //  @Qualifier
   // @Bean
 //   public Queue QueueSTATUS() {        return new ActiveMQQueue(STATUS_QUEUE);    }

 //   @Qualifier
 //   @Bean
// public Topic TopicSTATUS() {
//        return new ActiveMQTopic(STATUS_TOPIC);
//    }


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setTrustAllPackages(true);
        factory.setBrokerURL(brokerUrl);
        return factory;
    }
    @Autowired(required = false)
    public MessageConverter messageConverter;



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
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    @Bean
    public MessageConverter messageConverter() {
       // FactoryMessageConverter converter = new FactoryMessageConverter();
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }

}