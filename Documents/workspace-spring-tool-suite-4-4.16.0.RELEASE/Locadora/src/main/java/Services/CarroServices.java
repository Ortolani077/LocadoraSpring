package Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Carro;
import Model.Fabricante;
import Model.Modelo;
import Repository.CarroRepository;
import Repository.FabricanteRepository;
import Repository.ModeloRepository;

@Service
public class CarroServices {

    private final CarroRepository carroRepository;
    private final ModeloRepository modeloRepository;
    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public CarroServices(CarroRepository carroRepository, ModeloRepository modeloRepository,
            FabricanteRepository fabricanteRepository) {
        this.carroRepository = carroRepository;
        this.modeloRepository = modeloRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    // Método para listar todos os carros
    public List<Carro> listarTodosCarros() {
        return (List<Carro>) carroRepository.findAll();
    }

    // Método para buscar um carro pelo ID
    public Optional<Carro> buscarCarroPorId(Long id) {
        return carroRepository.findById(id);
    }
 
    
    
    
 // Método para cadastrar um carro
    public Carro cadastrarCarro(Carro carro) {
        // Verificar se o fabricante e o modelo existem no banco de dados
        Optional<Fabricante> fabricanteOptional = fabricanteRepository.findById(carro.getFabricanteId());
        Optional<Modelo> modeloOptional = modeloRepository.findById(carro.getModeloId());

        // Verificar se ambos existem
        if (fabricanteOptional.isPresent() && modeloOptional.isPresent()) {
            // Associar o ID do fabricante e o modelo ao carro
            carro.setFabricanteId(fabricanteOptional.get().getId());
            carro.setModelo(modeloOptional.get());
            
            // Salvar e retornar o carro cadastrado
            return carroRepository.save(carro);
        } else {
            // Se o fabricante ou o modelo não existirem, lançar uma exceção
            throw new IllegalArgumentException("Fabricante ou modelo não encontrado");
        }
    }

    
    
    
    
    
    
    


    // Método para atualizar um carro
    public Carro atualizarCarro(Long id, Carro carroAtualizado) {
        // Verificar se o carro com o ID fornecido existe no banco de dados
        Optional<Carro> carroExistenteOptional = carroRepository.findById(id);
        
        if (carroExistenteOptional.isPresent()) {
            Carro carroExistente = carroExistenteOptional.get();
            
            // Verificar se o fabricante e o modelo do carro atualizado existem no banco de dados
            Optional<Fabricante> fabricanteOptional = fabricanteRepository.findById(carroAtualizado.getFabricanteId());
            Optional<Modelo> modeloOptional = modeloRepository.findById(carroAtualizado.getModeloId());
            
            // Verificar se ambos existem
            if (fabricanteOptional.isPresent() && modeloOptional.isPresent()) {
                // Atualizar os campos do carro existente com os novos dados
                carroExistente.setFabricanteId(id);
                carroExistente.setModelo(modeloOptional.get());
                carroExistente.setPlaca(carroAtualizado.getPlaca());
                carroExistente.setCor(carroAtualizado.getCor());
                carroExistente.setDisponivel(carroAtualizado.getDisponivel());
                carroExistente.setAno(carroAtualizado.getAno());
                carroExistente.setValorLocacao(carroAtualizado.getValorLocacao());
                
                // Salvar e retornar o carro atualizado
                return carroRepository.save(carroExistente);
            } else {
                // Se o fabricante ou o modelo não existirem, lançar uma exceção
                throw new IllegalArgumentException("Fabricante ou modelo não encontrado");
            }
        } else {
            // Se o carro com o ID fornecido não existir, lançar uma exceção
            throw new IllegalArgumentException("Carro com o ID fornecido não encontrado");
        }
    }



    // Método para deletar um carro
    public void deletarCarro(Long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Carro com o ID fornecido não encontrado");
        }
    }
}
