package com.smoothstack.uthopia.dao;

import com.smoothstack.uthopia.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDAO extends JpaRepository<Airport, String> {

}
