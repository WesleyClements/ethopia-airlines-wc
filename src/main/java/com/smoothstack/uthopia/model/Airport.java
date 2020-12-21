package com.smoothstack.uthopia.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@XmlRootElement
@Entity
@Table(name = "airport")
public class Airport {
  @Id
  @Column(name = "iata_id")
  private String iataId;

  @Column(name = "city")
  private String city;

  @JsonManagedReference
  @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
  private Set<Route> routes = new HashSet<>();

  public String getIataId() {
    return this.iataId;
  }

  public void setIataId(final String value) {
    this.iataId = value;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(final String value) {
    this.city = value;
  }

  public Set<Route> getRoutes() {
    return routes;
  }

  public void setRoutes(final Set<Route> routes) {
    this.routes = routes;
  }
}
