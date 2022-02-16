package com.example.lm.xpto.systems.api.cities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class CityDTO implements Serializable {

    @JsonProperty("idIBGE")
    private Long ibgeId;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("capital")
    private Boolean capital;

    @JsonProperty("longitude")
    @Column(precision = 10, scale = 4)
    private BigDecimal lon;

    @JsonProperty("latitude")
    @Column(precision = 10, scale = 4)
    private BigDecimal lat;

    @JsonProperty("semAcentos")
    private String noAccents;

    @JsonProperty("nomeAlternativo")
    private String alternativeNames;

    @JsonProperty("microregiao")
    private String microregion;

    @JsonProperty("mesoregiao")
    private String mesoregion;
}