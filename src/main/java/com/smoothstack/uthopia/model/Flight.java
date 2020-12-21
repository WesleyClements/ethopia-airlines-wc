package com.smoothstack.uthopia.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.Formula;

@XmlRootElement
@Entity
@Table(name = "flight")
public class Flight {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "flight_id")
  private Integer flightId;

  @Column(name = "route_id", insertable = false, updatable = false)
  private Integer routeId;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "route_id")
  private Route route;

  @JsonProperty("departure")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Etc/GMT")
  @Column(name = "departure_time")
  private LocalDateTime departureTime;

  @Column(name = "capacity")
  private Integer capacity;

  @Formula("capacity - (SELECT COUNT(a.flight_id) FROM flight_bookings AS a WHERE a.flight_id=flight_id)")
  private Integer availableSeats;

  @Column(name = "seat_price")
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

  public Route getRoute() {
    return route;
  }

  public void setRoute(final Route route) {
    this.route = route;
  }

  public String getOrigin() {
    return this.route.getOriginId();
  }

  public String getDestination() {
    return this.route.getDestinationId();
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

  public Integer getAvailableSeats() {
    return availableSeats;
  }

  public Double getSeatPrice() {
    return seatPrice;
  }

  public void setSeatPrice(final Double seatPrice) {
    this.seatPrice = seatPrice;
  }
}
