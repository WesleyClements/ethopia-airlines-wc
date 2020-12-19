package com.smoothstack.uthopia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "airport")
public class Airport {
  @Id
  @Column(name = "iata_id")
  String iataId;

  @Column(name = "city")
  String city;

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
}
