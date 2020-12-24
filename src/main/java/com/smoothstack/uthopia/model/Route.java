package com.smoothstack.uthopia.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@Entity
@Table(name = "route")
public class Route {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "route_id")
  private Integer routeId;

  @JsonProperty("origin")
  @Column(name = "origin_id")
  private String originId;

  @JsonProperty("destination")
  @Column(name = "destination_id")
  private String destinationId;

  @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
  private Set<Flight> flights = new HashSet<>();

  public Integer getRouteId() {
    return routeId;
  }

  public void setRouteId(final Integer routeId) {
    this.routeId = routeId;
  }

  public String getOriginId() {
    return originId;
  }

  public void setOriginId(final String originId) {
    this.originId = originId;
  }

  public String getDestinationId() {
    return destinationId;
  }

  public void setDestinationId(final String destinationId) {
    this.destinationId = destinationId;
  }

  public Set<Flight> getFlights() {
    return flights;
  }

  public void setFlights(final Set<Flight> flights) {
    this.flights = flights;
  }

}
