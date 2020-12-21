package com.smoothstack.uthopia.service;

import java.util.List;
import java.util.Optional;

import com.smoothstack.uthopia.dao.FlightDAO;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.model.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
  @Autowired
  private FlightDAO flightDAO;

  public List<Flight> findAll() {
    return flightDAO.findAll();
  }

  public Flight findById(final Integer id) throws NotFoundException {
    final Optional<Flight> optional = flightDAO.findById(id);
    if (!optional.isPresent())
      throw new NotFoundException("cannot find route with id: " + id);
    return optional.get();
  }

  public Flight create(final Flight flight) {
    flightDAO.save(flight);
    return flight;
  }
}
