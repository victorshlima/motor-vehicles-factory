package com.motorcompany.motorvehiclesfactory.dao;

import com.motorcompany.config.domain.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class VehicleDaoImpl implements VehicleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Vehicle findById(Long id) {
        return null;
    }

    @Override
    public List<Vehicle> findAll() {
        return null;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        entityManager.persist(vehicle);
     return  vehicle;
    }


//    @Override
//    public List<Vehicle> findAll() {
//        return entityManager
//                .createQuery("select v from Vehicle v", Vehicle.class)
//                .getResultList();
//    }

}
