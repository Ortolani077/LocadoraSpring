package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Fabricante;
import Repository.FabricanteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    // Método para listar todos os fabricantes
    public List<Fabricante> listarTodosFabricantes() {
        return (List<Fabricante>) fabricanteRepository.findAll();
    }

    // Método para buscar um fabricante pelo ID
    public Optional<Fabricante> buscarFabricantePorId(Long id) {
        return fabricanteRepository.findById(id);
    }

    // Método para salvar um novo fabricante
    public Fabricante salvarFabricante(Fabricante fabricante) {
        return fabricanteRepository.save(fabricante);
    }

    // Método para atualizar um fabricante existente
    public Fabricante atualizarFabricante(Long id, Fabricante fabricante) {
        Optional<Fabricante> fabricanteOptional = fabricanteRepository.findById(id);
        if (fabricanteOptional.isPresent()) {
            Fabricante fabricanteExistente = fabricanteOptional.get();
            fabricanteExistente.setNome(fabricante.getNome());
            return fabricanteRepository.save(fabricanteExistente);
        } else {
            throw new RuntimeException("Fabricante não encontrado com o ID: " + id);
        }
    }

    // Método para excluir um fabricante pelo ID
    public void excluirFabricante(Long id) {
        fabricanteRepository.deleteById(id);
    }
}
