package com.example.lm.xpto.systems.api.cities.repository;

import com.example.lm.xpto.systems.api.cities.domain.City;

import java.util.List;

public interface CityRepositoryQuery {

    List<City> findByColumn(String col, String val) throws Exception;

    Long getQtdeByColumn(String col) throws Exception;
}
