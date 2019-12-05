package com.motorcompany.resource.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.jms.Topic;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import io.swagger.annotations.ApiOperation;
//@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class Status {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;



    @GetMapping("/status")
    //@ApiOperation(value = "Return the Status of Application", response = String.class)
    @ResponseStatus(HttpStatus.OK)
    //verificar db
    public String StatusApplication() {
        jmsTemplate.convertAndSend(queue, "topic");

        return "Online";
    }

    @GetMapping("/status/{message}")
    public String publish(@PathVariable("message")
                          final String message) {
        jmsTemplate.convertAndSend(queue, message);

        return "Published Successfully";
    }


}
