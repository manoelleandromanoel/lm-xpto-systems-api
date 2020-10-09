package com.example.lm.xpto.systems.api.cities.service;

import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.domain.UFNumberOfCitiesDTO;
import com.example.lm.xpto.systems.api.cities.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.awt.geom.Point2D;
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

    public String getMoreDistantCities() {
        List<City> allCities = cityRepository.findAll();

        double ultimaDistancia = 0;
        City cidadeDistante1 = null;
        City cidadeDistante2 = null;

        for (final City city1 : allCities) {
            for (final City city2 : allCities) {
                final double distancia =
                        Point2D.distance(
                                city1.getLon().doubleValue(),
                                city1.getLat().doubleValue(),
                                city2.getLon().doubleValue(),
                                city2.getLat().doubleValue()
                        );
                if (distancia > ultimaDistancia) {
                    ultimaDistancia = distancia;
                    cidadeDistante1 = city1;
                    cidadeDistante2 = city2;
                }
            }
        }

        return "As cidades mais distantes são: " + cidadeDistante1.getName() + "/" + cidadeDistante1.getUf() +
                " e " + cidadeDistante2.getName() + "/" + cidadeDistante1.getUf() +
                ". Distância de: " + String.valueOf(ultimaDistancia);
    }
}
