package com.example.lm.xpto.systems.api.cities.repository.impl;

import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.repository.CityRepositoryQuery;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CityRepositoryImpl implements CityRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> uploadFile(MultipartFile file) throws Exception {
        Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

        CsvToBean<City> csvToBean = new CsvToBeanBuilder<City>(reader)
                .withType(City.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withSkipLines(1) //ignorar a linha do cabeçalho
                .build();

        return csvToBean.parse();
    }

    @Override
    public List<City> getCapitalCitiesOrderedByName() throws Exception {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<City> root = criteria.from(City.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(root.get("capital"), Boolean.TRUE));

        criteria.where(predicates.toArray(new Predicate[predicates.size()]));

        criteria.orderBy(builder.asc(root.get("name")));

        TypedQuery<City> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<String> getCityByState(String uf) throws Exception {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<City> root = criteria.from(City.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(root.get("uf"), uf.toUpperCase()));

        criteria.where(predicates.toArray(new Predicate[predicates.size()]));

        criteria.orderBy(builder.asc(root.get("name")));

        TypedQuery<City> query = manager.createQuery(criteria);

        List<City> cities = query.getResultList();

        List<String> ret = new ArrayList<>();

        for (City c : cities) {
            ret.add(c.getName());
        }

        return ret;
    }

    @Override
    public List<City> findByColumn(String col, String val) throws Exception {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<City> root = criteria.from(City.class);

        List<Predicate> predicates = filterByColumn(col, val, builder, root);

        criteria.where(predicates.toArray(new Predicate[predicates.size()]));

        criteria.orderBy(builder.asc(root.get("name")));

        TypedQuery<City> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    private List<Predicate> filterByColumn(String col, String val, CriteriaBuilder builder, Root<City> root) throws Exception {

        List<Predicate> predicate = new ArrayList<>();

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
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<City> root = criteria.from(City.class);

        try {
            if ("capital".equals(col)) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(builder.equal(root.get("capital"), Boolean.TRUE));
                criteria.where(predicates.toArray(new Predicate[predicates.size()]));
            } else {
                criteria.select(root.get(col)).distinct(true);
            }

            TypedQuery<City> query = manager.createQuery(criteria);

            return (long) query.getResultList().size();
        } catch (Exception e) {
            throw new Exception("Não foi possível contar os registros da coluna: [" + col + "]");
        }

    }
}
