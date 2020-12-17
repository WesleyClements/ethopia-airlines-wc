package com.smoothstack.ethopiaairlines.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.smoothstack.ethopiaairlines.model.Airport;
import com.smoothstack.ethopiaairlines.util.AirportRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AirportDAO {

  private JdbcTemplate jdbcTemplate;

  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public void setDataSource(final DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("airport");
  }

  public List<Airport> getAirports() {
    return jdbcTemplate.query("SELECT * FROM airport", new AirportRowMapper());
  }

  public Airport getAirport(final String id) {
    final String query = "SELECT * FROM airport WHERE iata_id = ?";
    return jdbcTemplate.queryForObject(query, new Object[] { id }, new int[] { Types.CHAR }, new AirportRowMapper());
  }

  public int createAirport(final Airport airport) {
    final Map<String, Object> values = new HashMap<>();
    values.put("iata_id", airport.getIataId());
    values.put("city", airport.getCity());
    return simpleJdbcInsert.execute(values);
  }
}
