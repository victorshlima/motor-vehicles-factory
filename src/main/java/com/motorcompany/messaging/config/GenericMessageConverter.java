package com.motorcompany.messaging.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.motorcompany.domain.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;

@EnableJms
@Component
public class GenericMessageConverter implements MessageConverter {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(GenericMessageConverter.class);

    @Autowired
    ObjectMapper mapper;

    @Override
    public Message toMessage(Object object, Session session)
            throws JMSException {
        Factory factory = (Factory) object;
        String payload = null;
        try {
            payload = mapper.writeValueAsString(factory);
            LOGGER.info("outbound json='{}'", payload);
        } catch (JsonProcessingException e) {
            LOGGER.error("error converting form factory", e);
        }
        TextMessage message = session.createTextMessage();
        message.setText(payload);
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();
        message.getObjectProperty("");
        LOGGER.info("inbound json='{}'", payload);
        Factory factory = null;
        try {
            factory = mapper.readValue(payload, Factory.class);
        } catch (Exception e) {
            LOGGER.error("error converting to factory", e);
        }
        return factory;
    }

    public Object JsonUnMarshaller(Object message, Class<?> type) throws JMSException, IOException {
        TextMessage textMessage = (TextMessage) message;
        String jsonString = textMessage.getText();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonString);
        Object factory = mapper.readValue(jsonString, type);
        return factory;
    }

    public Object JsonUnMarshaller(String jsonString, Class<?> type)
            throws JsonParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonString);
        Object factory = mapper.readValue(jsonString, type);
        return factory;
    }
}
