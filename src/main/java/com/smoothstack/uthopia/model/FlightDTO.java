package com.smoothstack.uthopia.model;

import java.time.LocalDateTime;

public class FlightDTO {
  private Integer flightId;
  private Integer routeId;
  private String origin;
  private String destination;
  private LocalDateTime departure;
  private Integer capacity;
  private Integer availableSeats;
  private Double seatPrice;

  public Integer getFlightId() {
    return flightId;
  }

  public void setFlightId(final Integer flightId) {
    this.flightId = flightId;
  }

  public Integer getRouteId() {
    return routeId;
  }

  public void setRouteId(final Integer routeId) {
    this.routeId = routeId;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(final String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(final String destination) {
    this.destination = destination;
  }

  public LocalDateTime getDeparture() {
    return departure;
  }

  public void setDeparture(final LocalDateTime departure) {
    this.departure = departure;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(final Integer capacity) {
    this.capacity = capacity;
  }

  public Integer getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(final Integer availableSeats) {
    this.availableSeats = availableSeats;
  }

  public Double getSeatPrice() {
    return seatPrice;
  }

  public void setSeatPrice(final Double seatPrice) {
    this.seatPrice = seatPrice;
  }
}