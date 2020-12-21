package com.smoothstack.uthopia.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.smoothstack.uthopia.model.Flight;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightDAO extends JpaRepository<Flight, Integer> {
  List<Flight> findAllByRouteOriginId(final String originId);

  List<Flight> findAllByRouteDestinationId(final String destinationId);

  List<Flight> findAllByDepartureTimeAfterAndDepartureTimeBefore(final LocalDateTime departureStart,
      final LocalDateTime departureEnd);

  List<Flight> findAllByRouteOriginIdAndRouteDestinationId(final String originId, final String destinationId);

  List<Flight> findAllByRouteOriginIdAndDepartureTimeAfterAndDepartureTimeBefore(final String originId,
      final LocalDateTime departureStart, final LocalDateTime departureEnd);

  List<Flight> findAllByRouteDestinationIdAndDepartureTimeAfterAndDepartureTimeBefore(final String destinationId,
      final LocalDateTime departureStart, final LocalDateTime departureEnd);

  List<Flight> findAllByRouteOriginIdAndRouteDestinationIdAndDepartureTimeAfterAndDepartureTimeBefore(
      final String originId, final String destinationId, final LocalDateTime departureStart,
      final LocalDateTime departureEnd);
}
