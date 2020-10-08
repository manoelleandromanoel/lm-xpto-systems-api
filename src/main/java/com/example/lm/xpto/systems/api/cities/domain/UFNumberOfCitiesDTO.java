package com.example.lm.xpto.systems.api.cities.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class UFNumberOfCitiesDTO implements Serializable {

	@JsonProperty("UF")
	private final String uf;

	@JsonProperty("NumeroCidades")
	private final Long numberOfCities;

	public UFNumberOfCitiesDTO() {
		this(null, null);
	}
	
	public UFNumberOfCitiesDTO(final String uf, final Long numberOfCities) {
		this.uf = uf;
		this.numberOfCities = numberOfCities;
	}

	public String getUf() {
		return uf;
	}

	public Long getNumberOfCities() {
		return numberOfCities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UFNumberOfCitiesDTO that = (UFNumberOfCitiesDTO) o;
		return Objects.equals(uf, that.uf) &&
				Objects.equals(numberOfCities, that.numberOfCities);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uf, numberOfCities);
	}

	@Override
	public String toString() {
		return "UFNumberOfCitiesDTO{" +
				"uf='" + uf + '\'' +
				", numberOfCities=" + numberOfCities +
				'}';
	}
}
