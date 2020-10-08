package com.example.lm.xpto.systems.api.cities.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ibge_id == null) ? 0 : ibge_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (ibge_id == null) {
			if (other.ibge_id != null)
				return false;
		} else if (!ibge_id.equals(other.ibge_id))
			return false;
		return true;
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
