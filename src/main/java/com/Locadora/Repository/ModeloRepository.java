package com.Locadora.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Locadora.Model.Modelo;

import com.Locadora.Model.*;

@Repository("modelos")
public interface ModeloRepository extends CrudRepository<Modelo, Long> {
    // Métodos do repositório
}
