package com.example.lm.xpto.systems.api.cities.controller;

import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.domain.UFNumberOfCitiesDTO;
import com.example.lm.xpto.systems.api.cities.repository.CityRepository;
import com.example.lm.xpto.systems.api.cities.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CityController {

    private final CityRepository cityRepository;

    private final CityService cityService;

    @Autowired
    public CityController(
            final CityRepository cityRepository,
            final CityService cityService
    ) {
        this.cityRepository = cityRepository;
        this.cityService = cityService;
    }

    /**
     * 1. LER o arquivo CSV para a base de dados
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload") // , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            List<City> cities = cityRepository.uploadFile(file);

            List<City> importedCities = cityRepository.saveAll(cities);

            return ResponseEntity.status(HttpStatus.CREATED).body(importedCities.size() + " cidades importadas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao integrar arquivo: \n\n" + e.getMessage());
        }
    }

    /**
     * 2. Retornar as cidades que são capitais ordenadas por nome
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/capitais")
    public List<City> getCapitalCitiesOrderedByName() throws Exception {
        return cityRepository.getCapitalCitiesOrderedByName();
    }

    /**
     * 3. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/tamanho/estados/cidades")
    public List<UFNumberOfCitiesDTO> getStatesWithTheLargestAndSmallestNumberOfCities() throws Exception {
        return cityService.getStatesWithTheLargestAndSmallestNumberOfCities();
    }

    /**
     * 4. Retornar a quantidade de cidades por estado;
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/estados")
    public List<UFNumberOfCitiesDTO> getNumberOfCitiesByState() throws Exception {
        return cityRepository.getNumberOfCitiesByState();
    }

    /**
     * 5. Obter os dados da cidade informando o id do IBGE
     *
     * @param ibge_id
     * @return
     */
    @GetMapping("/{ibge_id}")
    public ResponseEntity<Optional<City>> findById(@PathVariable Long ibge_id) {
        Optional<City> cidade = cityRepository.findById(ibge_id);

        return cidade.isPresent() ? ResponseEntity.ok(cidade) : ResponseEntity.notFound().build();
    }

    /**
     * 6. Retornar o nome das cidades baseado em um estado selecionado;
     *
     * @param uf
     * @return
     * @throws Exception
     */
    @GetMapping("/by/estado/{uf}")
    public List<String> getCityByState(@PathVariable String uf) throws Exception {
        return cityRepository.getCityByState(uf);
    }

    /**
     * 7. Permitir adicionar uma nova City
     *
     * @param city
     * @param response
     * @return
     */
    @PostMapping
    public ResponseEntity<City> addCidade(@Valid @RequestBody City city, HttpServletResponse response) {
        try {
            City citySalva = cityRepository.save(city);

            return ResponseEntity.status(HttpStatus.CREATED).body(citySalva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(city);
        }
    }

    /**
     * 8. Permitir deletar uma cidade
     *
     * @param ibge_id
     */
    @DeleteMapping("/{ibge_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long ibge_id) {
        cityRepository.deleteById(ibge_id);
    }

    /**
     * 9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string
     *
     * @param col
     * @param val
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> findByColumn(@RequestParam("col") String col, @RequestParam("val") String val) {
        try {
            List<City> cities = cityRepository.findByColumn(col, val);

            return ResponseEntity.status(HttpStatus.CREATED).body(cities);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * 10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais
     *
     * @param col
     * @return
     * @throws Exception
     */
    @GetMapping("by/column")
    public ResponseEntity<Long> getQtdeByColumn(@RequestParam("col") String col) throws Exception {
        if (null != col) {
            return ResponseEntity.ok(cityRepository.getQtdeByColumn(col));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * 11. Retornar a quantidade de registros total;
     *
     * @return
     */
    @GetMapping("/total")
    public Long getQtdeCidades() {
        return cityRepository.count();
    }

    /**
     * 12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta);
     *
     * @return
     */
    @GetMapping("/distancia")
    public String getMoreDistantCities() {
        return cityService.getMoreDistantCities();
    }
}
