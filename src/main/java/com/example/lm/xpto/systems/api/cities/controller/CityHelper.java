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
        return CityDTO.builder()
                .ibgeId(city.getIbgeId())
                .uf(city.getUf())
                .name(city.getName())
                .capital(city.getCapital())
                .lon(city.getLon())
                .lat(city.getLat())
                .noAccents(city.getNoAccents())
                .alternativeNames(city.getAlternativeNames())
                .microregion(city.getMicroregion())
                .mesoregion(city.getMesoregion())
                .build();
    }

    public static City toCity(final CityDTO cityDTO) {
        return City.builder()
                .ibgeId(cityDTO.getIbgeId())
                .uf(cityDTO.getUf())
                .name(cityDTO.getName())
                .capital(cityDTO.getCapital())
                .lon(cityDTO.getLon())
                .lat(cityDTO.getLat())
                .noAccents(cityDTO.getNoAccents())
                .alternativeNames(cityDTO.getAlternativeNames())
                .microregion(cityDTO.getMicroregion())
                .mesoregion(cityDTO.getMesoregion())
                .build();
    }
}