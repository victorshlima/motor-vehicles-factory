package com.motorcompany.messaging.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motorcompany.domain.Factory;
import com.motorcompany.messaging.FactoryMessageConverter;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.util.ByteSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.core.JsonParseException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

import static com.motorcompany.messaging.config.ActiveMQConfig.FACTORY_QUEUE;
import static com.motorcompany.messaging.config.ActiveMQConfig.STATUS_QUEUE;



@Component
public class Consumer {
    private static Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    JmsTemplate jmsTemplate;

     @JmsListener(destination = STATUS_QUEUE)
    public void consumer(String message) {
        System.out.println(message);

    }


    public Object JsonUnMarshaller(String jsonString, Class<?> type)
            throws JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonString);
        Object object =  mapper.readValue(jsonString, type);
        return object;
    }


}