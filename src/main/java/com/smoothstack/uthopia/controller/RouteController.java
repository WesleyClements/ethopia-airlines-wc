package com.smoothstack.uthopia.controller;

import java.util.List;
import java.util.Map;

import com.smoothstack.exception.BadRequestException;
import com.smoothstack.uthopia.model.Route;
import com.smoothstack.uthopia.model.RouteDTO;
import com.smoothstack.uthopia.service.RouteService;

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
@RequestMapping(path = "/api/routes")
public class RouteController {
  @Autowired
  private RouteService routeService;

  @GetMapping("")
  public @ResponseBody List<Route> getRoutes(@RequestParam final Map<String, String> params) {
    final String origin = params.get("origin");
    final String destination = params.get("destination");
    if (origin == null && destination == null)
      return routeService.findAll();
    else
      return routeService.findAllWithOriginAndDestination(origin, destination);
  }

  @GetMapping("/{id}")
  public @ResponseBody Route getRouteById(@PathVariable final Integer id) {
    return routeService.findById(id);
  }

  @PostMapping("")
  public @ResponseBody Route getRouteById(@RequestBody final RouteDTO routeDTO) throws BadRequestException {
    return routeService.create(routeDTO);
  }
}
