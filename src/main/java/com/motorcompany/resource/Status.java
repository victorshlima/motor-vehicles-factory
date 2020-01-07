package com.motorcompany.resource;

import com.motorcompany.domain.Factory;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class Status {

    @Autowired
    JmsTemplate jmsTemplate;

    @ApiOperation(value="Get Status Server", response = String.class)
    @GetMapping("/status")
    public String StatusApplication() {
        return "Online";
    }

  }
