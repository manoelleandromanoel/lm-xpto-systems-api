package com.example.lm.xpto.systems.api.cities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CityDTO implements Serializable {

    @JsonProperty("idIBGE")
    private final Long ibge_id;

    @JsonProperty("uf")
    private final String uf;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("capital")
    private final Boolean capital;

    @JsonProperty("longitude")
    @Column(precision = 10, scale = 4)
    private final BigDecimal lon;

    @JsonProperty("latitude")
    @Column(precision = 10, scale = 4)
    private final BigDecimal lat;

    @JsonProperty("semAcentos")
    private final String no_accents;

    @JsonProperty("nomeAlternativo")
    private final String alternative_names;

    @JsonProperty("microregiao")
    private final String microregion;

    @JsonProperty("mesoregiao")
    private final String mesoregion;

    public CityDTO(
            Long ibge_id,
            String uf,
            String name,
            Boolean capital,
            BigDecimal lon,
            BigDecimal lat,
            String no_accents,
            String alternative_names,
            String microregion,
            String mesoregion
    ) {
        this.ibge_id = ibge_id;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.lon = lon;
        this.lat = lat;
        this.no_accents = no_accents;
        this.alternative_names = alternative_names;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    public CityDTO() {
        this(null, null, null, null, null, null, null, null, null, null);
    }

    public Long getIbge_id() {
        return ibge_id;
    }

    public String getUf() {
        return uf;
    }

    public String getName() {
        return name;
    }

    public Boolean getCapital() {
        return capital;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public String getNo_accents() {
        return no_accents;
    }

    public String getAlternative_names() {
        return alternative_names;
    }

    public String getMicroregion() {
        return microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return Objects.equals(ibge_id, cityDTO.ibge_id) &&
                Objects.equals(uf, cityDTO.uf) &&
                Objects.equals(name, cityDTO.name) &&
                Objects.equals(capital, cityDTO.capital) &&
                Objects.equals(lon, cityDTO.lon) &&
                Objects.equals(lat, cityDTO.lat) &&
                Objects.equals(no_accents, cityDTO.no_accents) &&
                Objects.equals(alternative_names, cityDTO.alternative_names) &&
                Objects.equals(microregion, cityDTO.microregion) &&
                Objects.equals(mesoregion, cityDTO.mesoregion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ibge_id, uf, name, capital, lon, lat, no_accents, alternative_names, microregion, mesoregion);
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "ibge_id=" + ibge_id +
                ", uf='" + uf + '\'' +
                ", name='" + name + '\'' +
                ", capital=" + capital +
                ", lon=" + lon +
                ", lat=" + lat +
                ", no_accents='" + no_accents + '\'' +
                ", alternative_names='" + alternative_names + '\'' +
                ", microregion='" + microregion + '\'' +
                ", mesoregion='" + mesoregion + '\'' +
                '}';
    }
}
