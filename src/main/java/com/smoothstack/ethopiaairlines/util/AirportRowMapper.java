package com.smoothstack.ethopiaairlines.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.smoothstack.ethopiaairlines.model.Airport;

import org.springframework.jdbc.core.RowMapper;

public class AirportRowMapper implements RowMapper<Airport> {

  @Override
  public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
    final Airport airport = new Airport();
    airport.setIataId(rs.getString("iata_id"));
    airport.setCity(rs.getString("city"));
    return airport;
  }

}
