package com.motorcompany.motorvehiclesfactory.dao;

import com.motorcompany.config.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VehicleDao extends CrudRepository<Vehicle, Long> {

    // void deleteByModel_code(int id);


    //  Vehicle findAllId(long id);

}
