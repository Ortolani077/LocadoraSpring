package com.Locadora.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Locadora.Model.Fabricante;
import com.Locadora.Model.Modelo;
import com.Locadora.Repository.FabricanteRepository;
import com.Locadora.Repository.ModeloRepository;

@Service
public class ModeloServices {

    private final ModeloRepository modeloRepository;
    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public ModeloServices(ModeloRepository modeloRepository, FabricanteRepository fabricanteRepository) {
        this.modeloRepository = modeloRepository;
        this.fabricanteRepository = fabricanteRepository;
    }

    // Método para listar todos os modelos
    public List<Modelo> listarTodosModelos() {
        return (List<Modelo>) modeloRepository.findAll();
    }

    // Método para buscar um modelo pelo ID
    public Optional<Modelo> buscarModeloPorId(Long id) {
        return modeloRepository.findById(id);
    }

    // Método para cadastrar um modelo
    public Modelo cadastrarModelo(Modelo modelo) {
        Optional<Fabricante> fabricanteOptional = fabricanteRepository.findById(modelo.getFabricante());
        if (fabricanteOptional.isPresent()) {
            return modeloRepository.save(modelo);
        } else {
            throw new IllegalArgumentException("Fabricante com o ID fornecido não encontrado");
        }
    }

    // Método para atualizar um modelo
    public Modelo atualizarModelo(Long id, Modelo modeloAtualizado) {
        if (modeloRepository.existsById(id)) {
            modeloAtualizado.setId(id); // Garante que o ID do modelo atualizado seja o mesmo do ID fornecido
            return modeloRepository.save(modeloAtualizado);
        } else {
            throw new IllegalArgumentException("Modelo com o ID fornecido não encontrado");
        }
    }

    // Método para deletar um modelo
    public void deletarModelo(Long id) {
        if (modeloRepository.existsById(id)) {
            modeloRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Modelo com o ID fornecido não encontrado");
        }
    }
}
