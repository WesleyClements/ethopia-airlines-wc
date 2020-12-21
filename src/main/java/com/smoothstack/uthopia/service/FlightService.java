package com.smoothstack.uthopia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.smoothstack.uthopia.dao.FlightDAO;
import com.smoothstack.uthopia.dao.RouteDAO;
import com.smoothstack.uthopia.exception.BadRequestException;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.model.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
  @Autowired
  private RouteDAO routeDAO;
  @Autowired
  private FlightDAO flightDAO;

  public List<Flight> findAll() {
    return flightDAO.findAll();
  }

  public List<Flight> findAllWithDepartureDate(final LocalDate departureDate) {
    if (departureDate == null)
      return flightDAO.findAll();
    else
      return flightDAO.findAllByDepartureTimeAfterAndDepartureTimeBefore(departureDate.atStartOfDay(),
          departureDate.atStartOfDay().plusDays(1));
  }

  public List<Flight> findAllWithOriginAndDestination(final String origin, final String destination) {
    if (destination == null && origin == null)
      return flightDAO.findAll();
    else if (destination == null)
      return flightDAO.findAllByRouteOriginId(origin);
    else if (origin == null)
      return flightDAO.findAllByRouteDestinationId(destination);
    else
      return flightDAO.findAllByRouteOriginIdAndRouteDestinationId(origin, destination);
  }

  public List<Flight> findAllWithOriginAndDestinationAndDepartureDate(final String origin, final String destination,
      final LocalDate departureDate) {
    if (origin == null && destination == null && departureDate == null)
      return flightDAO.findAll();
    else if (departureDate == null)
      return findAllWithOriginAndDestination(origin, destination);
    else if (origin == null && destination == null)
      return findAllWithDepartureDate(departureDate);
    else if (destination == null)
      return flightDAO.findAllByRouteOriginIdAndDepartureTimeAfterAndDepartureTimeBefore(origin,
          departureDate.atStartOfDay(), departureDate.atStartOfDay().plusDays(1));
    else if (origin == null)
      return flightDAO.findAllByRouteDestinationIdAndDepartureTimeAfterAndDepartureTimeBefore(destination,
          departureDate.atStartOfDay(), departureDate.atStartOfDay().plusDays(1));
    else
      return flightDAO.findAllByRouteOriginIdAndRouteDestinationIdAndDepartureTimeAfterAndDepartureTimeBefore(origin,
          destination, departureDate.atStartOfDay(), departureDate.atStartOfDay().plusDays(1));
  }

  public Flight findById(final Integer id) throws NotFoundException {
    final Optional<Flight> optional = flightDAO.findById(id);
    if (!optional.isPresent())
      throw new NotFoundException("cannot find route with id: " + id);
    return optional.get();
  }

  public Flight create(final Flight flight) throws BadRequestException {
    try {
      flight.setRoute(routeDAO.getOne(flight.getRouteId()));
      flightDAO.save(flight);
    } catch (DataIntegrityViolationException e) {
      throw new BadRequestException();
    } catch (EntityNotFoundException e) {
      throw new BadRequestException();
    }
    return flight;
  }

  public void delete(final Integer id) throws NotFoundException {
    try {
      flightDAO.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundException();
    }
  }
}
