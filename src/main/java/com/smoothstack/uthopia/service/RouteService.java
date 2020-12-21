package com.smoothstack.uthopia.service;

import java.util.List;
import java.util.Optional;

import com.smoothstack.uthopia.dao.RouteDAO;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.model.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
  @Autowired
  private RouteDAO routeDAO;

  public List<Route> findAll() {
    return routeDAO.findAll();
  }

  public List<Route> findAllWithOriginAndDestination(final String origin, final String destination) {
    if (destination == null)
      return routeDAO.findAllByOrigin(origin);
    else if (origin == null)
      return routeDAO.findAllByDestination(destination);
    else
      return routeDAO.findAllByOriginAndDestination(origin, destination);
  }

  public Route findById(final Integer id) throws NotFoundException {
    final Optional<Route> optional = routeDAO.findById(id);
    if (!optional.isPresent())
      throw new NotFoundException("cannot find route with id: " + id);
    return optional.get();
  }

  public Route create(final Route route) {
    routeDAO.save(route);
    return route;
  }
}
