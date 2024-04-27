package com.Locadora.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Locadora.Controller.FabricanteController;
import com.Locadora.Model.Fabricante;

@Repository
public interface FabricanteRepository extends CrudRepository<Fabricante, Long> {
    // Método para buscar um fabricante pelo ID
    Optional<Fabricante> findById(Long id);

	Optional<Fabricante> findById(Fabricante fabricanteId);
}
