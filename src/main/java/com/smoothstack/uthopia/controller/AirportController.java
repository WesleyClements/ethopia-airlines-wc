package com.smoothstack.uthopia.controller;

import java.util.List;

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

  @GetMapping("")
  public @ResponseBody List<Airport> getAirports() {
    return airportService.findAll();
  }

  @GetMapping("/{id}")
  public @ResponseBody Airport getAirportById(@PathVariable final String id) {
    return airportService.findById(id);
  }

  @PostMapping("")
  public @ResponseBody Airport createAirport(@RequestBody final Airport airport) {
    return airportService.create(airport);
  }
}
