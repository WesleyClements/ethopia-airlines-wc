package com.smoothstack.uthopia.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.smoothstack.uthopia.exception.BadRequestException;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.model.Flight;
import com.smoothstack.uthopia.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/flights")
public class FlightController {
  @Autowired
  private FlightService flightService;

  @GetMapping
  public @ResponseBody List<Flight> getFlights(@RequestParam final Map<String, String> params) {
    final String origin = params.get("origin");
    final String destination = params.get("destination");
    final String departure = params.get("departure");
    if (origin == null && destination == null && departure == null)
      return flightService.findAll();
    else if (departure == null)
      return flightService.findAllWithOriginAndDeparture(origin, destination);
    else {
      final LocalDate departureDate = LocalDate.parse(departure);
      return flightService.findAllWithOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
    }
  }

  @PostMapping
  public @ResponseBody Flight createFlight(@RequestBody final Flight flight) throws BadRequestException {
    return flightService.create(flight);
  }

  @GetMapping("/{id}")
  public @ResponseBody Flight getFlightById(@PathVariable final Integer id) throws NotFoundException {
    return flightService.findById(id);
  }

}
