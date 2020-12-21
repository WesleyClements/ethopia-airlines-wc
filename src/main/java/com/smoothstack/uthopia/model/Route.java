package com.smoothstack.uthopia.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@Entity
@Table(name = "route")
public class Route {
  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "route_id")
  private Integer routeId;

  @JsonProperty("origin")
  @Column(name = "origin_id", insertable = false, updatable = false)
  private String originId;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin_id")
  private Airport origin;

  @JsonProperty("destination")
  @Column(name = "destination_id", insertable = false, updatable = false)
  private String destinationId;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "destination_id")
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

  public String getOriginId() {
    return originId;
  }

  public void setOriginId(final String originId) {
    this.originId = originId;
  }

  public Airport getDestination() {
    return destination;
  }

  public void setDestination(final Airport destination) {
    this.destination = destination;
  }

  public String getDestinationId() {
    return destinationId;
  }

  public void setDestinationId(final String destinationId) {
    this.destinationId = destinationId;
  }

}
