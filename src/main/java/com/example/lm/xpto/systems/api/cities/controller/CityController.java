package com.example.lm.xpto.systems.api.cities.controller;

import com.example.lm.xpto.systems.api.cities.CityResource;
import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.dto.CityDTO;
import com.example.lm.xpto.systems.api.cities.dto.UFNumberOfCitiesDTO;
import com.example.lm.xpto.systems.api.cities.repository.CityRepository;
import com.example.lm.xpto.systems.api.cities.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController implements CityResource {

    private final CityRepository cityRepository;

    private final CityService cityService;

    @Autowired
    public CityController(final CityRepository cityRepository, final CityService cityService) {
        this.cityRepository = cityRepository;
        this.cityService = cityService;
    }

    @Override
    public ResponseEntity<String> uploadFile(@RequestParam("file") final MultipartFile file) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(cityService.uploadFile(file).size() + " cidades importadas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao integrar arquivo: \n\n" + e.getMessage());
        }
    }

    @Override
    public List<CityDTO> getCapitalCitiesOrderedByName() {
        return CityHelper.toCityDTOList(cityService.getCapitalCitiesOrderedByName());
    }

    @Override
    public List<UFNumberOfCitiesDTO> getStatesWithTheLargestAndSmallestNumberOfCities() throws Exception {
        return cityService.getStatesWithTheLargestAndSmallestNumberOfCities();
    }

    @Override
    public List<UFNumberOfCitiesDTO> getNumberOfCitiesByState() throws Exception {
        return cityRepository.getNumberOfCitiesByState();
    }

    @Override
    public ResponseEntity<CityDTO> findById(@PathVariable Long ibge_id) {
        Optional<City> city = cityRepository.findById(ibge_id);

        return city.map(c -> ResponseEntity.ok(CityHelper.toCityDTO(c))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public List<String> getCityByState(@PathVariable String uf) {
        return cityService.getCityByState(uf);
    }

    @Override
    public ResponseEntity<CityDTO> addCidade(@Validated @RequestBody CityDTO cityDTO) {
        try {
            CityDTO savedCity = CityHelper.toCityDTO(cityRepository.save(CityHelper.toCity(cityDTO)));

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cityDTO);
        }
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long ibge_id) {
        cityRepository.deleteById(ibge_id);
    }

    @Override
    public ResponseEntity<List<CityDTO>> findByColumn(
            @RequestParam("col") String col,
            @RequestParam("val") String val
    ) throws Exception {
        List<CityDTO> cities = CityHelper.toCityDTOList(cityRepository.findByColumn(col, val));

        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @Override
    public ResponseEntity<Long> getQtdeByColumn(@RequestParam("col") String col) throws Exception {
        if (null != col) {
            return ResponseEntity.ok(cityRepository.getQtdeByColumn(col));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @Override
    public Long getQtdeCidades() {
        return cityRepository.count();
    }

    @Override
    public String getMoreDistantCities() {
        return cityService.getMoreDistantCities();
    }
}