package com.motorcompany.config.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.motorcompany.config.domain.Result;

import java.util.List;

@Repository
public interface ResultDao extends CrudRepository<Result, Long> {
    List<Result> findAll();
    Result save(Result result);
}