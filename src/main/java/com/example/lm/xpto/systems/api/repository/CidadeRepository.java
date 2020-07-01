package com.example.lm.xpto.systems.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lm.xpto.systems.api.model.Cidade;
import com.example.lm.xpto.systems.api.repository.cidade.CidadeRepositoryQuery;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryQuery {

}
