package com.example.lm.xpto.systems.api.cities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class UFNumberOfCitiesDTO implements Serializable {

    private String uf;

    @JsonProperty("numeroCidades")
    private Long numberOfCities;
}