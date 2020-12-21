package com.smoothstack.uthopia.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "flight")
public class Flight {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "flight_id")
  private Integer flightId;

  @Column(name = "route_id")
  private Integer routeId;

  @Column(name = "departure_time")
  private LocalDateTime departureTime;

  @Column(name = "capacity")
  private Integer capacity;

  @Formula("capacity")
  private Integer availableSeats;

  @Column(name = "seat_price")
  private Double seatPrice;

  public Integer getFlightId() {
    return flightId;
  }

  public Integer getRouteId() {
    return routeId;
  }

  public void setRouteId(final Integer routeId) {
    this.routeId = routeId;
  }

  public LocalDateTime getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(final LocalDateTime departureTime) {
    this.departureTime = departureTime;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(final Integer capacity) {
    this.capacity = capacity;
  }

  public Double getSeatPrice() {
    return seatPrice;
  }

  public void setSeatPrice(final Double seatPrice) {
    this.seatPrice = seatPrice;
  }
}
