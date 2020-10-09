package com.example.lm.xpto.systems.api.cities.repository;

import com.example.lm.xpto.systems.api.cities.domain.City;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CityRepositoryQuery {

    List<City> uploadFile(MultipartFile file) throws Exception;

    List<City> getCapitalCitiesOrderedByName() throws Exception;

    List<String> getCityByState(String uf) throws Exception;

    List<City> findByColumn(String col, String val) throws Exception;

    Long getQtdeByColumn(String col) throws Exception;
}
