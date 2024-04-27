package com.Locadora.Repository;

import com.Locadora.Model.Carro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends CrudRepository<Carro, Long> {
    // Adicione métodos específicos do carro, se necessário
}
