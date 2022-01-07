package com.example.lm.xpto.systems.api.cities.controller;

import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.dto.CityDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CityHelper {

    public static List<CityDTO> toCityDTOList(List<City> cities) {
        return cities.stream().map(CityHelper::toCityDTO).collect(Collectors.toList());
    }

    public static CityDTO toCityDTO(City city) {
        return new CityDTO(
                city.getIbge_id(),
                city.getUf(),
                city.getName(),
                city.getCapital(),
                city.getLon(),
                city.getLat(),
                city.getNo_accents(),
                city.getAlternative_names(),
                city.getMicroregion(),
                city.getMesoregion()
        );
    }

    public static City toCity(CityDTO cityDTO) {
        return new City(
                cityDTO.getIbge_id(),
                cityDTO.getUf(),
                cityDTO.getName(),
                cityDTO.getCapital(),
                cityDTO.getLon(),
                cityDTO.getLat(),
                cityDTO.getNo_accents(),
                cityDTO.getAlternative_names(),
                cityDTO.getMicroregion(),
                cityDTO.getMesoregion()
        );
    }
}
