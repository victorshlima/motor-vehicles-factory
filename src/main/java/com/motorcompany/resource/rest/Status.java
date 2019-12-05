package com.motorcompany.resource.rest;


import com.motorcompany.domain.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

//import static com.motorcompany.messaging.config.ConfigQueue.STATUS_QUEUE;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import io.swagger.annotations.ApiOperation;
//@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class Status {

    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping("/status")
    //@ApiOperation(value = "Return the Status of Application", response = String.class)
    @ResponseStatus(HttpStatus.OK)
    //verificar db
    public String StatusApplication(Factory factory) {
        return "Online";
    }

    @GetMapping("/status/{message}")
    public String publish(@PathVariable("message")
                          final String message) {
        System.out.println(" Enviando mensagem" + message);
     //   jmsTemplate.convertAndSend(STATUS_QUEUE, message);
        return "Published Successfully";
    }
}
