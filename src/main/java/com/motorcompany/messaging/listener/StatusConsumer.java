package com.motorcompany.messaging.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatusConsumer {
    private static Logger log = LoggerFactory.getLogger(StatusConsumer.class);

    public static final String STATUS_QUEUE = "status.queue";
    public static final String STATUS_TOPIC= "status.topic";

    @Autowired
    JmsTemplate jmsTemplate;
     @JmsListener(destination = STATUS_QUEUE)
    public void consumer(String message) {
        System.out.println(message);
    }
  }