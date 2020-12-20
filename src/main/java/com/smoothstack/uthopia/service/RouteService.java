package com.smoothstack.uthopia.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.smoothstack.exception.BadRequestException;
import com.smoothstack.uthopia.dao.AirportDAO;
import com.smoothstack.uthopia.dao.RouteDAO;
import com.smoothstack.uthopia.model.Airport;
import com.smoothstack.uthopia.model.Route;
import com.smoothstack.uthopia.model.RouteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
  @Autowired
  private AirportDAO airportDAO;
  @Autowired
  private RouteDAO routeDAO;

  public List<Route> findAll() {
    return routeDAO.findAll();
  }

  public List<Route> findAllWithOriginAndDestination(final String origin, final String destination) {
    if (destination == null)
      return routeDAO.findByOriginIataId(origin);
    else if (origin == null)
      return routeDAO.findByDestinationIataId(destination);
    else
      return routeDAO.findByOriginIataIdAndDestinationIataId(origin, destination);
  }

  public Route findById(final Integer id) {
    final Optional<Route> optional = routeDAO.findById(id);
    if (!optional.isPresent())
      throw new EntityNotFoundException("cannot find route with id: " + id);
    return optional.get();
  }

  public Route create(final RouteDTO routeDTO) throws BadRequestException {
    final Optional<Airport> originAirport = airportDAO.findById(routeDTO.getOrigin());
    if (!originAirport.isPresent())
      throw new BadRequestException("invalid origin airport: " + routeDTO.getOrigin());
    final Optional<Airport> destinationAirport = airportDAO.findById(routeDTO.getOrigin());
    if (!destinationAirport.isPresent())
      throw new BadRequestException("invalid destination airport: " + routeDTO.getOrigin());
    final Route route = new Route();
    route.setOrigin(originAirport.get());
    route.setDestination(destinationAirport.get());
    routeDAO.save(route);
    return route;
  }
}
