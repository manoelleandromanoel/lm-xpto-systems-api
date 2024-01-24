package com.example.lm.xpto.systems.api.cities.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum State {

    ACRE("Acre", "AC"),
    ALAGOAS("Alagoas", "AL"),
    AMAPA("Amapá", "AP"),
    AMAZONAS("Amazonas", "AM"),
    BAHIA("Bahia", "BA"),
    CEARA("Ceará", "CE"),
    DISTRITOFEDERAL("Distrito Federal", "DF"),
    ESPIRITOSANTO("Espírito Santo", "ES"),
    GOIAS("Goiás", "GO"),
    MARANHAO("Maranhão", "MA"),
    MATOGROSSO("Mato Grosso", "MT"),
    MATOGROSSODOSUL("Mato Grosso do Sul", "MS"),
    MINASGERAIS("Minas Gerais", "MG"),
    PARA("Pará", "PA"),
    PARAIBA("Paraíba", "PB"),
    PARANA("Paraná", "PR"),
    PERNAMBUCO("Pernambuco", "PE"),
    PIAUI("Piauí", "PI"),
    RIODEJANEIRO("Rio de Janeiro", "RJ"),
    RIOGRANDEDONORTE("Rio Grande do Norte", "RN"),
    RIOGRANDEDOSUL("Rio Grande do Sul", "RS"),
    RONDONIA("Rondônia", "RO"),
    RORAIMA("Roraima", "RR"),
    SANTACATARINA("Santa Catarina", "SC"),
    SAOPAULO("São Paulo", "SP"),
    SERGIPE("Sergipe", "SE"),
    TOCANTINS("Tocantins", "TO");

    private final String name;

    private final String initials;

    @JsonCreator
    public static State forAlias(final String alias) {
        Optional<State> stateOptional = Arrays.stream(values()).filter(so -> alias.equalsIgnoreCase(so.getName())).findFirst();
        if (stateOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Unknown enum type %s, allowed alias are %s", alias, Arrays.toString(values())));
        }
        return stateOptional.get();
    }

    @JsonValue
    public String toName() {
        return this.name;
    }
}