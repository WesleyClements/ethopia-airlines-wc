package com.smoothstack.uthopia.controller;

import java.util.List;
import java.util.Map;

import com.smoothstack.uthopia.exception.BadRequestException;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.model.Route;
import com.smoothstack.uthopia.service.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/routes")
public class RouteController {
  @Autowired
  private RouteService routeService;

  @GetMapping
  public @ResponseBody List<Route> getRoutes(@RequestParam final Map<String, String> params) {
    final String origin = params.get("origin");
    final String destination = params.get("destination");
    if (origin == null && destination == null)
      return routeService.findAll();
    else
      return routeService.findAllWithOriginAndDestination(origin, destination);
  }

  @PostMapping
  public @ResponseBody ResponseEntity<Route> getRouteById(@RequestBody final Route route) throws BadRequestException {
    return new ResponseEntity<>(routeService.create(route), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public @ResponseBody Route getRouteById(@PathVariable final Integer id) throws NotFoundException {
    return routeService.findById(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteRoute(@PathVariable final Integer id) throws NotFoundException {
    routeService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
