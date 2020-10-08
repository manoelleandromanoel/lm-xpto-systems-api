package com.example.lm.xpto.systems.api.cities.service;

import com.example.lm.xpto.systems.api.cities.domain.UFNumberOfCitiesDTO;
import com.example.lm.xpto.systems.api.cities.repository.CityRepository;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<UFNumberOfCitiesDTO> getStatesWithTheLargestAndSmallestNumberOfCities() throws Exception {
        List<UFNumberOfCitiesDTO> cities = cityRepository.getNumberOfCitiesByState();

        return Arrays.asList(
                cities.stream().min(Comparator.comparing(UFNumberOfCitiesDTO::getNumberOfCities)).get(),
                cities.stream().max(Comparator.comparing(UFNumberOfCitiesDTO::getNumberOfCities)).get()
        );
    }
}
