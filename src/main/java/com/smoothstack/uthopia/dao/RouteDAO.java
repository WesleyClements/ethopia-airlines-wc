package com.smoothstack.uthopia.dao;

import com.smoothstack.uthopia.model.Route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDAO extends JpaRepository<Route, Integer> {

}
