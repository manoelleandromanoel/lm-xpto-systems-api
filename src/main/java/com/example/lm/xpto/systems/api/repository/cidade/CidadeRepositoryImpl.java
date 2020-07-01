package com.example.lm.xpto.systems.api.repository.cidade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.web.multipart.MultipartFile;

import com.example.lm.xpto.systems.api.model.Cidade;
import com.example.lm.xpto.systems.api.model.UfQtdeCidade;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CidadeRepositoryImpl implements CidadeRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> uploadFile(MultipartFile file) throws Exception {
		Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

		CsvToBean<Cidade> csvToBean = new CsvToBeanBuilder<Cidade>(reader)
			.withType(Cidade.class)
			.withIgnoreLeadingWhiteSpace(true)
			.withSkipLines(1) //ignorar a linha do cabeçalho
			.build();

		return csvToBean.parse();
	}
	
	@Override
	public List<Cidade> getCapitalCitiesOrderedByName() throws Exception {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("capital"), Boolean.TRUE));

		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		
		criteria.orderBy(builder.asc(root.get("name")));
		
		TypedQuery<Cidade> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	@Override
	public List<Cidade> getStatesWithTheLargestAndSmallestNumberOfCities() throws Exception {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
//		predicates.add(builder.co );
		
		return null;
	}
	
	@Override
	public List<UfQtdeCidade> getNumberOfCitiesByState() throws Exception {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		criteria.multiselect(root.get("uf"), root.get("name"));
		
		List<Predicate> predicates = new ArrayList<>();
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));

//		TypedQuery<Cidade> query = manager.createQuery(criteria);
		
		Query query = manager.createQuery(criteria);
		
		//Executing Query to get particular records in a List from UserInfo 
		List<Object[]> collection = query.getResultList();
		
		return query.getResultList();
	}
	
	@Override
	public List<String> getCityByState(String uf) throws Exception {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get("uf"), uf.toUpperCase()));

		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		
		criteria.orderBy(builder.asc(root.get("name")));
		
		TypedQuery<Cidade> query = manager.createQuery(criteria);
		
		List<Cidade> cidades = query.getResultList(); 
		
		List<String> ret = new ArrayList<String>();

		for (Cidade c : cidades) {
			ret.add(c.getName());
		}
		
		return ret;
	}
	
	@Override
	public List<Cidade> findByColumn(String col, String val) throws Exception {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		List<Predicate> predicates = filterByColumn(col, val, builder, root);
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		
		criteria.orderBy(builder.asc(root.get("name")));
		
		TypedQuery<Cidade> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private List<Predicate> filterByColumn(String col, String val, CriteriaBuilder builder, Root<Cidade> root) throws Exception {
		
		List<Predicate> predicate = new ArrayList<Predicate>();
		
		switch (col) {
		case "ibge_id": 
			predicate.add(builder.like(root.get("ibge_id"), val));
			break;
		case "uf":
			predicate.add(builder.like(root.get("uf"), val));
			break;
		case "name":
			predicate.add(builder.like(root.get("name"), "%" + val + "%"));
			break;
		case "capital":
			predicate.add(builder.equal(root.get("capital"), Boolean.getBoolean(val)));
			break;
		case "lon":
			predicate.add(builder.like(root.get("lon"), val));
			break;
		case "lat":
			predicate.add(builder.like(root.get("lat"), val));
			break;
		case "no_accents":
			predicate.add(builder.like(root.get("no_accents"), val));
			break;
		case "alternative_names":
			predicate.add(builder.like(root.get("alternative_names"), val));
			break;
		case "microregion":
			predicate.add(builder.like(root.get("microregion"), val));
			break;
		case "mesoregion":
			predicate.add(builder.like(root.get("mesoregion"), val));
			break;
		default:
			throw new Exception("Não foi possível encontrar a coluna: [" + col + "]");
		}
		
		return predicate;
	}
	
	@Override
	public Long getQtdeByColumn(String col) throws Exception {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);

		try {
			switch (col) {
			case "capital":
//				criteria.select(root.get(col), Boolean.TRUE);
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(builder.equal(root.get("capital"), Boolean.TRUE));
				criteria.where(predicates.toArray(new Predicate[predicates.size()]));
				break;
			default:
				criteria.select(root.get(col)).distinct(true);
				break;
			}
			
			TypedQuery<Cidade> query = manager.createQuery(criteria);
			
			return (long) query.getResultList().size();
		} catch (Exception e) {
			throw new Exception("Não foi possível contar os registros da coluna: [" + col + "]");
		}
		
	}
}
