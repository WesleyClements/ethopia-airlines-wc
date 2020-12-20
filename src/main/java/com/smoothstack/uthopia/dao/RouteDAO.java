package com.smoothstack.uthopia.dao;

import java.util.List;

import com.smoothstack.uthopia.model.Route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDAO extends JpaRepository<Route, Integer> {

  List<Route> findAllByOrigin(final String origin);

  List<Route> findAllByDestination(final String destination);

  List<Route> findAllByOriginAndDestination(final String originIataId, final String destinationIataId);
}
