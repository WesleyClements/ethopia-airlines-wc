package com.smoothstack.uthopia.model;

public class AirportDTO {
  private String iataCode;
  private String city;

  public String getIataCode() {
    return iataCode;
  }

  public void setIataCode(final String iataCode) {
    this.iataCode = iataCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }
}
