package Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Model.Carro;
import Model.Modelo;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "carros", path = "carros")
public interface CarroRepository extends CrudRepository<Carro, Long> {
    // Métodos do repositório
}
