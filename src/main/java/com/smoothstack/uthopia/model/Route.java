package com.smoothstack.uthopia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "route_id")
  private Integer routeId;

  @ManyToOne
  @JoinColumn(name = "origin_airport_id", nullable = false)
  private Airport origin;

  @ManyToOne
  @JoinColumn(name = "destination_airport_id", nullable = false)
  private Airport destination;

  public Integer getRouteId() {
    return routeId;
  }

  public Airport getOrigin() {
    return origin;
  }

  public void setOrigin(final Airport origin) {
    this.origin = origin;
  }

  public Airport getDestination() {
    return destination;
  }

  public void setDestination(final Airport destination) {
    this.destination = destination;
  }

}
