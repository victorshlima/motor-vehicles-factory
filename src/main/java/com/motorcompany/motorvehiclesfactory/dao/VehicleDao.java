package com.motorcompany.motorvehiclesfactory.dao;

import com.motorcompany.motorvehiclesfactory.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Long> {
    Vehicle findById(Long id);
    List<Vehicle> findAll();


  //  Vehicle findByModel_code(int model_code);
}

