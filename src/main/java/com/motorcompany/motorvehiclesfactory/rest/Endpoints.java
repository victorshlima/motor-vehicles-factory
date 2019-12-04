package com.motorcompany.motorvehiclesfactory.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("API")
public class Endpoints {


    @GetMapping("/model")
    @ResponseStatus(HttpStatus.OK)
    public String StatusApplication() {
        return "Online";
    }


}
