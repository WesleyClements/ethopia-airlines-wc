package com.smoothstack.uthopia.dao;

import com.smoothstack.uthopia.model.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightDAO extends JpaRepository<Flight, Integer> {

}
