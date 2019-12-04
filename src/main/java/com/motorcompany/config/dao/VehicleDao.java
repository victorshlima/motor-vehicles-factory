package com.motorcompany.config.dao;


import com.motorcompany.config.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VehicleDao extends CrudRepository<Vehicle, Long> {
    List<Vehicle> findAll();
    Vehicle save(Vehicle vehicle);
    Vehicle findByModelCode(int modelCode);

    @Transactional
    void deleteByModelCode(int modelCode);

    @Transactional
    void deleteById(long id);
}