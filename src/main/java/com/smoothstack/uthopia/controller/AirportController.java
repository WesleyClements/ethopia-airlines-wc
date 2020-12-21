package com.smoothstack.uthopia.controller;

import java.util.List;

import com.smoothstack.uthopia.exception.BadRequestException;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.model.Airport;
import com.smoothstack.uthopia.service.AirportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/airports")
public class AirportController {
  @Autowired
  private AirportService airportService;

  @GetMapping
  public @ResponseBody List<Airport> getAirports() {
    return airportService.findAll();
  }

  @PostMapping
  public @ResponseBody Airport createAirport(@RequestBody final Airport airport) throws BadRequestException {
    return airportService.create(airport);
  }

  @GetMapping("/{id}")
  public @ResponseBody Airport getAirportById(@PathVariable final String id) throws NotFoundException {
    return airportService.findById(id);
  }
}
