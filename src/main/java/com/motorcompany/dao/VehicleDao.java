package com.motorcompany.dao;


import com.motorcompany.domain.Factory;
import com.motorcompany.domain.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;





@Repository
public interface VehicleDao extends CrudRepository<Vehiculo, Long> {

    List<Vehiculo> findAll();
    Factory save(Factory factory);


    @Transactional
    void deleteById(long id);
}