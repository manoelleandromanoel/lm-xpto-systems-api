package com.example.lm.xpto.systems.api.cities;

import com.example.lm.xpto.systems.api.cities.dto.CityDTO;
import com.example.lm.xpto.systems.api.cities.dto.UFNumberOfCitiesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(value = "Cidade", tags = {"cidade"})
@RequestMapping("/cidades")
public interface CityResource {

    @ApiOperation(value = "1. Importa um arquivo CSV para a base de dados",
            nickname = "cidadesUploadPost",
            notes = "Essa operação importa um arquivo CSV para a base de dados."
    )
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sucesso ao importar arquivo.")})
    @PostMapping(value = "/upload")
    ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file
    );

    @ApiOperation(value = "2. Retorna as cidades que são capitais ordenadas por nome",
            nickname = "cidadesCapitaisGet",
            notes = "Essa operação retorna as cidades que são capitais ordenadas por nome."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao retornar as cidades que são capitais ordenadas por nome.")
    })
    @GetMapping("/capitais")
    List<CityDTO> getCapitalCitiesOrderedByName() throws Exception;

    @ApiOperation(value = "3. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades",
            nickname = "cidadesTamanhoEstadosCidadesGet",
            notes = "Essa operação retorna o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades.")
    })
    @GetMapping("/tamanho/estados/cidades")
    List<UFNumberOfCitiesDTO> getStatesWithTheLargestAndSmallestNumberOfCities() throws Exception;

    @ApiOperation(value = "4. Retornar a quantidade de cidades por estado",
            nickname = "cidadesEstadosGet",
            notes = "Essa operação retornar a quantidade de cidades por estado."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao retornar a quantidade de cidades por estado.")
    })
    @GetMapping("/estados")
    List<UFNumberOfCitiesDTO> getNumberOfCitiesByState() throws Exception;

    @ApiOperation(value = "5. Obter os dados da cidade informando o id do IBGE",
            nickname = "cidadesEstadosGet",
            notes = "Essa operação obtem os dados da cidade informando o id do IBGE."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao obter os dados da cidade informando o id do IBGE.")
    })
    @GetMapping("/{ibge_id}")
    ResponseEntity<CityDTO> findById(
            @PathVariable Long ibge_id
    );

    @ApiOperation(value = "6. Retornar o nome das cidades baseado em um estado selecionado",
            nickname = "cidadesByEstadoGet",
            notes = "Essa operação retorna o nome das cidades baseado em um estado selecionado."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao retornar o nome das cidades baseado em um estado selecionado.")
    })
    @GetMapping("/by/estado/{uf}")
    List<String> getCityByState(
            @PathVariable String uf
    ) throws Exception;

    @ApiOperation(value = "7. Permitir adicionar uma nova cidade",
            nickname = "cidadesPost",
            notes = "Essa operação permite adicionar uma nova cidade."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao adicionar uma nova cidade.")
    })
    @PostMapping
    ResponseEntity<CityDTO> addCidade(
            @Validated @RequestBody CityDTO cityDTO
    );

    @ApiOperation(value = "8. Permite deletar uma cidade",
            nickname = "cidadesDelete",
            notes = "Essa operação permite deletar uma cidade."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao deletar uma cidade.")
    })
    @DeleteMapping("/{ibge_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(
            @PathVariable Long ibge_id
    );

    @ApiOperation(value = "9. Permite selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string",
            nickname = "cidadesFindByColumn",
            notes = "Essa operação retorna uma lista de cidades filtrando por uma coluna do CSV."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao efetuar operação.")
    })
    @GetMapping
    ResponseEntity<List<CityDTO>> findByColumn(
            @RequestParam("col") String col,
            @RequestParam("val") String val
    ) throws Exception;

    @ApiOperation(value = "10. Retornar a quantidade de registros baseado em uma coluna. Não deve contar itens iguais",
            nickname = "cidadesByColumn",
            notes = "Essa operação retorna a quantidade de registros baseado em uma coluna."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao retornar a quantidade de registros.")
    })
    @GetMapping("by/column")
    ResponseEntity<Long> getQtdeByColumn(
            @RequestParam("col") String col
    ) throws Exception;

    @ApiOperation(value = "11. Retornar a quantidade de registros total",
            nickname = "cidadesTotal",
            notes = "Essa operação retorna a quantidade de registros total."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao retornar a quantidade de registros total.")
    })
    @GetMapping("/total")
    Long getQtdeCidades();

    @ApiOperation(value = "12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta)",
            nickname = "cidadesTotal",
            notes = "Essa operação retorna as cidades mais distantes com base na localização"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso ao retornar as cidades mais distantes.")
    })
    @GetMapping("/distancia")
    String getMoreDistantCities();
}
