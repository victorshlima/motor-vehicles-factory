package com.motorcompany.resource.rest;


import com.motorcompany.dao.VehicleDao;
import com.motorcompany.domain.Vehicle;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping(value = "v1")
public class Status {

    @GetMapping("/status")
    @ApiOperation(value = "Return the Status of Application", response = String.class)
    @ResponseStatus(HttpStatus.OK)
    //verificar db
    public String StatusApplication() {
        return "Online";
    }

}
