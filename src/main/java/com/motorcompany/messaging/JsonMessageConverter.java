//package com.motorcompany.messaging;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
//import org.springframework.cloud.cloudfoundry.com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.support.converter.MessageConversionException;
//import org.springframework.jms.support.converter.MessageConverter;
//import org.springframework.stereotype.Component;
//
//import javax.jms.BytesMessage;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//
//
//@EnableJms
//@Component
//    public class JsonMessageConverter implements MessageConverter {
//
//
//    private ObjectMapper mapper;
//
//    @Override
//    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
//        return ((TextMessage) message).getText();
//    }
//
//    @Override
//    public javax.jms.Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
//        String json;
//
//        try {
//
//            json = mapper.writeValueAsString(object);
//        } catch (Exception e) {
//            throw new MessageConversionException("Message cannot be parsed. ", e);
//        }
//
//        TextMessage message = session.createTextMessage();
//        message.setText(json);
//
//        return message;
//    }
//
//}