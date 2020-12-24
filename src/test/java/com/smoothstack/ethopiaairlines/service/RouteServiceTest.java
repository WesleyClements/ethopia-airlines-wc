package com.smoothstack.ethopiaairlines.service;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import java.util.ArrayList;
import java.util.List;
import com.smoothstack.uthopia.UthopiaAirlinesApplication;
import com.smoothstack.uthopia.dao.RouteDAO;
import com.smoothstack.uthopia.model.Route;
import com.smoothstack.uthopia.service.RouteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UthopiaAirlinesApplication.class)
public class RouteServiceTest {

  @Mock
  RouteDAO routeDAO;

  @InjectMocks
  RouteService routeService;

  List<Route> getRoutes() {
    final List<Route> routes = new ArrayList<Route>();
    {
      Route route = new Route();
      route.setOriginId("AAA");
      route.setOriginId("AAB");
      routes.add(route);
    }
    {
      Route route = new Route();
      route.setOriginId("AAC");
      route.setOriginId("AAB");
      routes.add(route);
    }
    {
      Route route = new Route();
      route.setOriginId("AAA");
      route.setOriginId("AAC");
      routes.add(route);
    }
    return routes;
  }


  @Test
  void getAllRoutes() throws Exception {
    final List<Route> routes = getRoutes();
    Mockito.when(routeDAO.findAll()).thenReturn(routes);
    assertIterableEquals(routes, routeService.findAll());
  }


  @Test
  void getAllRoutesByOriginAndDestination() throws Exception {
    final List<Route> routes = getRoutes();
    Mockito.when(routeDAO.findAll()).thenReturn(routes);
    assertIterableEquals(routes, routeService.findAll());
  }
}
