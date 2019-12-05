package com.motorcompany.dao;


import com.motorcompany.domain.Factory;
import com.motorcompany.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FactoryDao extends CrudRepository<Factory, Long> {
    List<Factory> findAll();
    Factory save(Factory factory);

    @Transactional
    void deleteById(long id);
}