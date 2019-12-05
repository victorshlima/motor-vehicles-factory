package com.motorcompany.dao;


import com.motorcompany.domain.Factory;
import com.motorcompany.domain.VehicleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VehicleModelDao extends CrudRepository<VehicleModel, Long> {
    List<VehicleModel> findAll();
    VehicleModel save(VehicleModel vehicle);
    VehicleModel findByModelCode(int modelCode);



    @Transactional
    void deleteByModelCode(int modelCode);

    @Transactional
    void deleteById(long id);
}