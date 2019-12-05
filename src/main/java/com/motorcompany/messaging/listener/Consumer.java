package com.motorcompany.messaging.listener;

import com.motorcompany.enums.messaging.ApplicationQueues;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "status.queue")
    public void consume(String message) {
        System.out.println("Received Message: " + message);
    }
}
//