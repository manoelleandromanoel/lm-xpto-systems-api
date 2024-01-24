package com.example.lm.xpto.systems.api.cities.repository.impl;

import com.example.lm.xpto.systems.api.cities.domain.City;
import com.example.lm.xpto.systems.api.cities.domain.City_;
import com.example.lm.xpto.systems.api.cities.repository.CityRepositoryQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CityRepositoryImpl implements CityRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> findByColumn(final String col, final String val) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<City> root = criteria.from(City.class);

        List<Predicate> predicates = filterByColumn(col, val, builder, root);

        criteria.where(predicates.toArray(new Predicate[predicates.size()]));

        criteria.orderBy(builder.asc(root.get(City_.NAME)));

        TypedQuery<City> query = entityManager.createQuery(criteria);

        return query.getResultList();
    }

    private List<Predicate> filterByColumn(String col, String val, CriteriaBuilder builder, Root<City> root) throws Exception {

        List<Predicate> predicate = new ArrayList<>();

        switch (col) {
            case City_.IBGE_ID:
                predicate.add(builder.like(root.get(City_.IBGE_ID), val));
                break;
            case City_.UF:
                predicate.add(builder.like(root.get(City_.UF), val));
                break;
            case City_.NAME:
                predicate.add(builder.like(root.get(City_.NAME), "%" + val + "%"));
                break;
            case City_.CAPITAL:
                predicate.add(builder.equal(root.get(City_.CAPITAL), Boolean.getBoolean(val)));
                break;
            case City_.LON:
                predicate.add(builder.like(root.get(City_.LON), val));
                break;
            case City_.LAT:
                predicate.add(builder.like(root.get(City_.LAT), val));
                break;
            case City_.NO_ACCENTS:
                predicate.add(builder.like(root.get(City_.NO_ACCENTS), val));
                break;
            case City_.ALTERNATIVE_NAMES:
                predicate.add(builder.like(root.get(City_.ALTERNATIVE_NAMES), val));
                break;
            case City_.MICROREGION:
                predicate.add(builder.like(root.get(City_.MICROREGION), val));
                break;
            case City_.MESOREGION:
                predicate.add(builder.like(root.get(City_.MESOREGION), val));
                break;
            default:
                throw new Exception("Não foi possível encontrar a coluna: [" + col + "]");
        }

        return predicate;
    }

    @Override
    public Long getQtdeByColumn(final String col) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<City> root = criteria.from(City.class);

        try {
            if (City_.CAPITAL.equals(col)) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(builder.equal(root.get(City_.CAPITAL), Boolean.TRUE));
                criteria.where(predicates.toArray(new Predicate[predicates.size()]));
            } else {
                criteria.select(root.get(col)).distinct(true);
            }

            TypedQuery<City> query = entityManager.createQuery(criteria);

            return (long) query.getResultList().size();
        } catch (Exception e) {
            throw new Exception("Não foi possível contar os registros da coluna: [" + col + "]");
        }
    }
}