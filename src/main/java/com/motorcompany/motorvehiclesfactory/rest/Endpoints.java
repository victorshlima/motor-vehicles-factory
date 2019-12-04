package com.motorcompany.motorvehiclesfactory.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@RestController
@RequestMapping("v1")
public class Endpoints {

    @GetMapping("/model")
    @ApiOperation(value = "Return the Status of Application", response = String.class)
    @ResponseStatus(HttpStatus.OK)
    public String StatusApplication() {
        return "Online";
    }


}
