package com.motorcompany.resource;
import com.motorcompany.domain.Factory;
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

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public String StatusApplication(Factory factory) {
        return "Online";
    }

    @GetMapping("/status/{message}")
    public String publish(@PathVariable("message")
                          final String message) {
        return "Online";
    }
}
