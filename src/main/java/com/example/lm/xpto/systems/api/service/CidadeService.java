package com.example.lm.xpto.systems.api.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lm.xpto.systems.api.model.Cidade;

public interface CidadeService extends JpaRepository<Cidade, Long> {

}
