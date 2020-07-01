package com.example.lm.xpto.systems.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lm.xpto.systems.api.model.Cidade;
import com.example.lm.xpto.systems.api.model.UfQtdeCidade;
import com.example.lm.xpto.systems.api.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	/**
	 * 1. LER o arquivo CSV para a base de dados
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/upload") // , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		try {
			List<Cidade> cidades = cidadeRepository.uploadFile(file);

			List<Cidade> cidadesSalva = cidadeRepository.saveAll(cidades);

			return ResponseEntity.status(HttpStatus.CREATED).body(cidadesSalva);
		} catch (Exception e) {
			return new ResponseEntity<>("Erro ao integrar arquivo: \n\n" + e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	/**
	 * 2. Retornar as cidades que são capitais ordenadas por nome
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/capitais")
	public List<Cidade> getCapitalCitiesOrderedByName() throws Exception {
		return cidadeRepository.getCapitalCitiesOrderedByName();
	}

	
	//TODO: 3. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades;
	/**
	 * 3. Retornar o nome do estado com a maior e menor quantidade de cidades e a
	 * quantidade de cidades
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/tamanho/estados/cidades")
	public List<Cidade> getStatesWithTheLargestAndSmallestNumberOfCities() throws Exception {
		return cidadeRepository.getStatesWithTheLargestAndSmallestNumberOfCities();
	}

	//TODO: 4. Retornar a quantidade de cidades por estado;
	@GetMapping("/estados")
	public List<UfQtdeCidade> getNumberOfCitiesByState() throws Exception {
		return cidadeRepository.getNumberOfCitiesByState();
	}
	
	/**
	 * 5. Obter os dados da cidade informando o id do IBGE
	 * 
	 * @param ibge_id
	 * @return
	 */
	@GetMapping("/{ibge_id}")
	public ResponseEntity<Optional<Cidade>> findById(@PathVariable Long ibge_id) {
		Optional<Cidade> cidade = cidadeRepository.findById(ibge_id);

		return cidade.isPresent() ? ResponseEntity.ok(cidade) : ResponseEntity.notFound().build();
	}

	/**
	 * 6. Retornar o nome das cidades baseado em um estado selecionado;
	 * @param uf
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/by/estado/{uf}")
	public List<String> getCityByState(@PathVariable String uf) throws Exception {
		return cidadeRepository.getCityByState(uf);
	}
	
	/**
	 * 7. Permitir adicionar uma nova Cidade
	 * 
	 * @param cidade
	 * @param response
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Cidade> addCidade(@Valid @RequestBody Cidade cidade, HttpServletResponse response) {
		try {
			Cidade cidadeSalva = cidadeRepository.save(cidade);

			return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cidade);
		}
	}

	/**
	 * 8. Permitir deletar uma cidade
	 * 
	 * @param id
	 */
	@DeleteMapping("/{ibge_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long ibge_id) {
		cidadeRepository.deleteById(ibge_id);
	}
	
	/**
	 * 9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string
	 * @param col
	 * @param val
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> findByColumn(@RequestParam("col") String col, @RequestParam("val") String val) {
		try {
			List<Cidade> cidades = cidadeRepository.findByColumn(col, val);

			return ResponseEntity.status(HttpStatus.CREATED).body(cidades);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	//TODO: 10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais;
	@GetMapping("by/column")
	public Long getQtdeByColumn(@RequestParam("col") String col) throws Exception {
		return cidadeRepository.getQtdeByColumn(col);
	}
	
	/**
	 * 11. Retornar a quantidade de registros total;
	 * 
	 * @param ibge_id
	 * @return
	 */
	@GetMapping("/total")
	public Long getQtdeCidades() {
		return cidadeRepository.count();
	}
	
	//TODO: 12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta);
}
