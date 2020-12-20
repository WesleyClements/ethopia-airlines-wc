package com.smoothstack.uthopia.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.smoothstack.uthopia.dao.RouteDAO;
import com.smoothstack.uthopia.model.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/routes")
public class RouteController {
  @Autowired
  private RouteDAO routeDAO;

  @GetMapping("")
  public @ResponseBody List<Route> getRoutes() {
    return routeDAO.findAll();
  }

  @GetMapping("/{id}")
  public @ResponseBody Route getRouteById(@PathVariable final Integer id) throws Exception {
    final Optional<Route> optional = routeDAO.findById(id);
    if (!optional.isPresent())
      throw new EntityNotFoundException("cannot find route with id: " + id);
    return optional.get();
  }

  @PostMapping("")
  public @ResponseBody Route getRouteById(@RequestBody final Route route) {
    routeDAO.save(route);
    return route;
  }
}
