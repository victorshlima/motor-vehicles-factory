package com.motorcompany.motorvehiclesfactory.dao;

import com.motorcompany.motorvehiclesfactory.model.Vehicle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "vehicle", path = "vehicle")
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {

	//List<Vehicle> findByModel_code(@Param("Model_code") String Model_code);

}
