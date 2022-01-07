package com.example.lm.xpto.systems.api.cities.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "CITY")
public class City {

    @Id
    @CsvBindByName
    @CsvBindByPosition(position = 0)
    private Long ibge_id;

    @CsvBindByName
    @CsvBindByPosition(position = 1)
    private String uf;

    @CsvBindByName
    @CsvBindByPosition(position = 2)
    private String name;

    @CsvBindByName
    @CsvBindByPosition(position = 3)
    private Boolean capital;

    @CsvBindByName
    @CsvBindByPosition(position = 4)
    private BigDecimal lon;

    @CsvBindByName
    @CsvBindByPosition(position = 5)
    private BigDecimal lat;

    @CsvBindByName
    @CsvBindByPosition(position = 6)
    private String no_accents;

    @CsvBindByName
    @CsvBindByPosition(position = 7)
    private String alternative_names;

    @CsvBindByName
    @CsvBindByPosition(position = 8)
    private String microregion;

    @CsvBindByName
    @CsvBindByPosition(position = 9)
    private String mesoregion;

    public City(Long ibge_id,
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

    public City() {
        this(null, null, null, null, null, null, null, null, null, null);
    }

    public Long getIbge_id() {
        return ibge_id;
    }

    public void setIbge_id(Long ibge_id) {
        this.ibge_id = ibge_id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getNo_accents() {
        return no_accents;
    }

    public void setNo_accents(String no_accents) {
        this.no_accents = no_accents;
    }

    public String getAlternative_names() {
        return alternative_names;
    }

    public void setAlternative_names(String alternative_names) {
        this.alternative_names = alternative_names;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(ibge_id, city.ibge_id) &&
                Objects.equals(uf, city.uf) &&
                Objects.equals(name, city.name) &&
                Objects.equals(capital, city.capital) &&
                Objects.equals(lon, city.lon) &&
                Objects.equals(lat, city.lat) &&
                Objects.equals(no_accents, city.no_accents) &&
                Objects.equals(alternative_names, city.alternative_names) &&
                Objects.equals(microregion, city.microregion) &&
                Objects.equals(mesoregion, city.mesoregion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ibge_id, uf, name, capital, lon, lat, no_accents, alternative_names, microregion, mesoregion);
    }

    @Override
    public String toString() {
        return "City{" +
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
