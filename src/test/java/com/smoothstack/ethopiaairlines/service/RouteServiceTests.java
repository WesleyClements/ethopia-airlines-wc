package com.smoothstack.ethopiaairlines.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.smoothstack.uthopia.UthopiaAirlinesApplication;
import com.smoothstack.uthopia.dao.RouteDAO;
import com.smoothstack.uthopia.exception.BadRequestException;
import com.smoothstack.uthopia.exception.NotFoundException;
import com.smoothstack.uthopia.exception.StateConflictException;
import com.smoothstack.uthopia.model.Route;
import com.smoothstack.uthopia.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest(classes = UthopiaAirlinesApplication.class)
public class RouteServiceTests {

  @Mock
  RouteDAO routeDAO;

  @InjectMocks
  RouteService routeService;

  List<Route> routes;

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


  @Test
  void getAllRoutes() throws Exception {
    final List<Route> routes = getRoutes();
    Mockito.when(routeDAO.findAll()).thenReturn(routes);
    assertIterableEquals(routes, routeService.findAll());
  }


  @Test
  void getAllRoutesByOriginAndDestination() throws Exception {
    final String origin = "AAA";
    final String destination = "AAB";
    final List<Route> routes = getRoutes();
    final List<Route> originFilteredRoutes = routes.stream()
        .filter((route) -> route.getOriginId() == origin).collect(Collectors.toList());
    final List<Route> destinationFilteredRoutes = routes.stream()
        .filter((route) -> route.getDestinationId() == destination).collect(Collectors.toList());
    final List<Route> originAndDestinationFilteredRoutes = routes.stream()
        .filter((route) -> route.getOriginId() == origin && route.getDestinationId() == destination)
        .collect(Collectors.toList());
    Mockito.when(routeDAO.findAll()).thenReturn(routes);
    Mockito.when(routeDAO.findAllByOriginId(origin)).thenReturn(originFilteredRoutes);
    Mockito.when(routeDAO.findAllByDestinationId(destination))
        .thenReturn(destinationFilteredRoutes);
    Mockito.when(routeDAO.findAllByOriginIdAndDestinationId(origin, destination))
        .thenReturn(originAndDestinationFilteredRoutes);

    assertIterableEquals(routes, routeService.findAllWithOriginAndDestination(null, null));
    assertIterableEquals(originFilteredRoutes,
        routeService.findAllWithOriginAndDestination(origin, null));
    assertIterableEquals(destinationFilteredRoutes,
        routeService.findAllWithOriginAndDestination(null, destination));
    assertIterableEquals(originAndDestinationFilteredRoutes,
        routeService.findAllWithOriginAndDestination(origin, destination));
  }

  @Test
  void findRouteById() throws NotFoundException {
    final Integer existentId = 2;
    final Integer nonExistentId = 4;
    final Route routeWithId = getRouteById(existentId);
    Mockito.when(routeDAO.findById(existentId)).thenReturn(Optional.of(routeWithId));
    Mockito.when(routeDAO.findById(nonExistentId)).thenReturn(Optional.empty());
    assertEquals(routeWithId, routeService.findById(existentId));
    assertThrows(NotFoundException.class, () -> routeService.findById(nonExistentId));
  }

  @Test
  void createRoute() throws BadRequestException, StateConflictException {
    final Route newRoute = new Route();
    newRoute.setOriginId("AAB");
    newRoute.setDestinationId("AAC");
    Mockito.when(routeDAO.save(newRoute)).thenReturn(newRoute);
    assertEquals(newRoute, routeService.create(newRoute));
  }

  @Test
  void deleteRoute() {
    final Integer existentIdToDelete = 2;
    final Integer nonExistentIdToDelete = 4;
    Mockito.doThrow(EmptyResultDataAccessException.class).when(routeDAO)
        .deleteById(nonExistentIdToDelete);
    assertDoesNotThrow(() -> routeService.delete(existentIdToDelete));
    assertThrows(NotFoundException.class, () -> routeService.delete(nonExistentIdToDelete));
  }
}
