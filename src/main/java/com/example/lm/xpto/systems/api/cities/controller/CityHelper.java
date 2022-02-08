package com.example.lm.xpto.systems.api.cities.controller;

import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.dto.CityDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CityHelper {

    public static List<CityDTO> toCityDTOList(final List<City> cities) {
        return cities.stream().map(CityHelper::toCityDTO).collect(Collectors.toList());
    }

    public static CityDTO toCityDTO(final City city) {
        return new CityDTO(
                city.getIbgeId(),
                city.getUf(),
                city.getName(),
                city.getCapital(),
                city.getLon(),
                city.getLat(),
                city.getNoAccents(),
                city.getAlternativeNames(),
                city.getMicroregion(),
                city.getMesoregion()
        );
    }

    public static City toCity(final CityDTO cityDTO) {
        return new City(
                cityDTO.getIbgeId(),
                cityDTO.getUf(),
                cityDTO.getName(),
                cityDTO.getCapital(),
                cityDTO.getLon(),
                cityDTO.getLat(),
                cityDTO.getNoAccents(),
                cityDTO.getAlternativeNames(),
                cityDTO.getMicroregion(),
                cityDTO.getMesoregion()
        );
    }
}