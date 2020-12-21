package com.smoothstack.uthopia.service;

import java.util.List;
import java.util.Optional;

import com.smoothstack.uthopia.dao.AirportDAO;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.model.Airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {
  @Autowired
  private AirportDAO airportDAO;

  public List<Airport> findAll() {
    return airportDAO.findAll();
  }

  public Airport findById(final String id) throws NotFoundException {
    final Optional<Airport> optional = airportDAO.findById(id);
    if (!optional.isPresent())
      throw new NotFoundException("cannot find airport with id: " + id);
    return optional.get();
  }

  public Airport create(final Airport airport) {
    airportDAO.save(airport);
    return airport;
  }
}
