package com.smoothstack.uthopia.controller;

import java.util.List;
import java.util.Optional;

import com.smoothstack.uthopia.dao.AirportDAO;
import com.smoothstack.uthopia.model.Airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/airports")
public class AirportController {
  @Autowired
  private AirportDAO airportDAO;

  @GetMapping("")
  public @ResponseBody List<Airport> getAirports() {
    return airportDAO.findAll();
  }

  @GetMapping("/{id}")
  public @ResponseBody Optional<Airport> getAirportById(@PathVariable final String id) {
    return airportDAO.findById(id);
  }

  @PostMapping("")
  public @ResponseBody Airport createAirport(@RequestBody final Airport airport) {
    airportDAO.save(airport);
    return airport;
  }
}
