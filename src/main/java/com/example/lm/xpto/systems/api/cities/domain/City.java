package com.example.lm.xpto.systems.api.cities.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Entity(name = "city")
public class City {

    @Id
    @CsvBindByName
    @CsvBindByPosition(position = 0)
    private Long ibgeId;

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
    private String noAccents;

    @CsvBindByName
    @CsvBindByPosition(position = 7)
    private String alternativeNames;

    @CsvBindByName
    @CsvBindByPosition(position = 8)
    private String microregion;

    @CsvBindByName
    @CsvBindByPosition(position = 9)
    private String mesoregion;
}