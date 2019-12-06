package com.motorcompany.dao;


import com.motorcompany.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleDao extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findAll();

    Vehicle findById(long id);
}