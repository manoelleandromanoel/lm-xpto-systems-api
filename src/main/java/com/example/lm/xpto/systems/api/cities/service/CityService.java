package com.example.lm.xpto.systems.api.cities.service;

import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.dto.UFNumberOfCitiesDTO;
import com.example.lm.xpto.systems.api.cities.repository.CityRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> uploadFile(MultipartFile file) throws Exception {
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

        CsvToBean<City> csvToBean = new CsvToBeanBuilder<City>(reader)
                .withType(City.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1) //ignorar a linha do cabeçalho
                .build();

        return cityRepository.saveAll(csvToBean.parse());
    }

    public List<City> getCapitalCitiesOrderedByName() {
        return cityRepository.findAll().stream()
                .filter(c -> Boolean.TRUE.equals(c.getCapital()))
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
    }

    public List<UFNumberOfCitiesDTO> getStatesWithTheLargestAndSmallestNumberOfCities() throws Exception {
        List<UFNumberOfCitiesDTO> cities = cityRepository.getNumberOfCitiesByState();

        return Arrays.asList(
                cities.stream().min(Comparator.comparing(UFNumberOfCitiesDTO::getNumberOfCities)).get(),
                cities.stream().max(Comparator.comparing(UFNumberOfCitiesDTO::getNumberOfCities)).get()
        );
    }

    public List<String> getCityByState(String uf) {
        List<City> cities = cityRepository.findAll().stream()
                .filter(c -> c.getUf().equalsIgnoreCase(uf))
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());

        List<String> ret = new ArrayList<>();

        for (City c : cities) {
            ret.add(c.getNo_accents());
        }

        return ret;
    }

    public String getMoreDistantCities() {
        List<City> allCities = cityRepository.findAll();

        double ultimaDistancia = 0;
        City cidadeDistante1 = new City();
        City cidadeDistante2 = new City();

        for (final City city1 : allCities) {
            for (final City city2 : allCities) {
                final double distancia = getDistance(city1.getLat().doubleValue(), city1.getLon().doubleValue(),
                        city2.getLat().doubleValue(), city2.getLon().doubleValue()
                );

                if (distancia > ultimaDistancia) {
                    ultimaDistancia = distancia;
                    cidadeDistante1 = city1;
                    cidadeDistante2 = city2;
                }
            }
        }

        return "As cidades mais distantes são: " + cidadeDistante1.getNo_accents() + "/" + cidadeDistante1.getUf() +
                " e " + cidadeDistante2.getNo_accents() + "/" + cidadeDistante2.getUf() +
                ". Distância de: " + BigDecimal.valueOf(ultimaDistancia).setScale(2, RoundingMode.HALF_UP) + " km";
    }

    private double getDistance(double lat1, double long1, double lat2, double long2) {
        int EARTH_RADIUS_KM = 6371;

        // Conversão de graus pra radianos das latitudes
        double firstLatToRad = Math.toRadians(lat1);
        double secondLatToRad = Math.toRadians(lat2);

        // Diferença das longitudes
        double deltaLongitudeInRad = Math.toRadians(long2 - long1);

        // Cálcula a distância entre os pontos
        return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
                * Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
                * Math.sin(secondLatToRad))
                * EARTH_RADIUS_KM;
    }

}
