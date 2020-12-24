package com.smoothstack.ethopiaairlines.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.smoothstack.uthopia.UthopiaAirlinesApplication;
import com.smoothstack.uthopia.dao.FlightDAO;
import com.smoothstack.uthopia.dao.RouteDAO;
import com.smoothstack.uthopia.exception.BadRequestException;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.exception.StateConflictException;
import com.smoothstack.uthopia.model.Flight;
import com.smoothstack.uthopia.model.Route;
import com.smoothstack.uthopia.service.FlightService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest(classes = UthopiaAirlinesApplication.class)
public class FlightServiceTests {
  @Mock
  RouteDAO routeDAO;
  @Mock
  FlightDAO flightDAO;

  @InjectMocks
  FlightService flightService;

  List<Route> routes;
  List<Flight> flights;

  List<Route> getRoutes() {
    if (routes != null)
      return routes;
    routes = new ArrayList<Route>();
    {
      final Route route = new Route();
      route.setRouteId(1);
      route.setOriginId("AAA");
      route.setOriginId("AAB");
      routes.add(route);
    }
    {
      final Route route = new Route();
      route.setRouteId(2);
      route.setOriginId("AAC");
      route.setOriginId("AAB");
      routes.add(route);
    }
    {
      final Route route = new Route();
      route.setRouteId(3);
      route.setOriginId("AAA");
      route.setOriginId("AAC");
      routes.add(route);
    }
    return routes;
  }

  Route getRouteById(final Integer id) {
    return getRoutes().stream().filter(route -> route.getRouteId() == id)
        .collect(Collectors.toList()).get(0);
  }

  List<Flight> getFlights() {
    if (flights != null)
      return flights;
    flights = new ArrayList<Flight>();
    {
      final Flight flight = new Flight();
      flight.setFlightId(1);
      flight.setRoute(getRouteById(1));
      flight.setDepartureTime(LocalDateTime.of(1990, 12, 10, 12, 53));
      flights.add(flight);
    }
    {
      final Flight flight = new Flight();
      flight.setFlightId(2);
      flight.setRoute(getRouteById(2));
      flight.setDepartureTime(LocalDateTime.of(1990, 12, 10, 12, 53));
      flights.add(flight);
    }
    {
      final Flight flight = new Flight();
      flight.setFlightId(3);
      flight.setRoute(getRouteById(3));
      flight.setDepartureTime(LocalDateTime.of(1990, 12, 11, 12, 53));
      flights.add(flight);
    }
    return flights;
  }

  Flight getFlightById(final Integer id) {
    return getFlights().stream().filter(flight -> flight.getFlightId() == id)
        .collect(Collectors.toList()).get(0);
  }

  @Test
  void getAllFlights() {
    final List<Flight> flights = getFlights();
    Mockito.when(flightDAO.findAll()).thenReturn(flights);
    assertIterableEquals(flights, flightService.findAll());
  }

  @Test
  void findFlightById() throws NotFoundException {
    final Integer existentId = 2;
    final Integer nonExistentId = 4;
    final Flight flightWithId = getFlightById(existentId);
    Mockito.when(flightDAO.findById(existentId)).thenReturn(Optional.of(flightWithId));
    Mockito.when(flightDAO.findById(nonExistentId)).thenReturn(Optional.empty());
    assertEquals(flightWithId, flightService.findById(existentId));
    assertThrows(NotFoundException.class, () -> flightService.findById(nonExistentId));
  }

  @Test
  void createFlight() throws BadRequestException, StateConflictException {
    final Integer routeId = 2;
    final Flight newFlight = new Flight();
    newFlight.setRouteId(routeId);
    newFlight.setDepartureTime(LocalDateTime.of(1990, 12, 11, 12, 53));
    Mockito.when(routeDAO.getOne(routeId)).thenReturn(getRouteById(routeId));
    Mockito.when(flightDAO.save(newFlight)).thenReturn(newFlight);
    assertEquals(newFlight, flightService.create(newFlight));
  }

  @Test
  void deleteFlight() {
    final Integer existentIdToDelete = 2;
    final Integer nonExistentIdToDelete = 4;
    Mockito.doThrow(EmptyResultDataAccessException.class).when(flightDAO)
        .deleteById(nonExistentIdToDelete);
    assertDoesNotThrow(() -> flightService.delete(existentIdToDelete));
    assertThrows(NotFoundException.class, () -> flightService.delete(nonExistentIdToDelete));
  }
}
