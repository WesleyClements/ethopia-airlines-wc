package com.smoothstack.uthopia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "route_id")
  private Integer routeId;

  @Column(name = "origin_id")
  private String origin;

  @Column(name = "destination_id")
  private String destination;

  public Integer getRouteId() {
    return routeId;
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

}
