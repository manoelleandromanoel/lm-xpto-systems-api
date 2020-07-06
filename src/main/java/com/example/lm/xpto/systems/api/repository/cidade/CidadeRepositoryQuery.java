package com.example.lm.xpto.systems.api.repository.cidade;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.lm.xpto.systems.api.model.Cidade;
import com.example.lm.xpto.systems.api.model.UfQtdeCidade;

public interface CidadeRepositoryQuery {

	public List<Cidade> uploadFile(MultipartFile file) throws Exception;
	
	public List<Cidade> getCapitalCitiesOrderedByName() throws Exception;

	public List<Cidade> getStatesWithTheLargestAndSmallestNumberOfCities() throws Exception;
	
//	@Query("select new com.example.lm.xpto.systems.api.model.UfQtdeCidade(uf.sigla, count(*) as qtde) "
//		   + "from com.example.lm.xpto.systems.api.model.Cidade c "
//		   + "join c.estado uf "
//		   + "group by uf.id "
//		   + "order by qtde desc ")
	public List<UfQtdeCidade> getNumberOfCitiesByState() throws Exception;

	public List<String> getCityByState(String uf) throws Exception;
	
	public List<Cidade> findByColumn(String col, String val) throws Exception;
	
	public Long getQtdeByColumn(String col) throws Exception;
}
