package com.smoothstack.uthopia.dao;

import java.time.LocalDate;
import java.util.List;

import com.smoothstack.uthopia.model.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightDAO extends JpaRepository<Flight, Integer> {
  List<Flight> findAllByRouteOriginId(final String originId);

  List<Flight> findAllByRouteDestinationId(final String destinationId);

  List<Flight> findAllByDepartureDate(final LocalDate departureDate);

  List<Flight> findAllByRouteOriginIdAndRouteDestinationId(final String originId, final String destinationId);

  List<Flight> findAllByRouteOriginIdAndDepartureDate(final String originId, final LocalDate departureDate);

  List<Flight> findAllByRouteDestinationIdAndDepartureDate(final String destinationId, final LocalDate departureDate);

  List<Flight> findAllByRouteOriginIdAndRouteDestinationIdAndDepartureDate(final String originId,
      final String destinationId, final LocalDate departureDate);
}
