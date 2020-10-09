package com.example.lm.xpto.systems.api.cities.repository;

import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.domain.UFNumberOfCitiesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, CityRepositoryQuery {

    @Query("select new com.example.lm.xpto.systems.api.cities.domain.UFNumberOfCitiesDTO(city.uf, count(*) as qtde) "
            + "  from com.example.lm.xpto.systems.api.cities.domain.City city "
            + " group by city.uf "
            + " order by city.uf ")
    List<UFNumberOfCitiesDTO> getNumberOfCitiesByState() throws Exception;
}
