package com.Locadora.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Locadora.Model.Carro;
import com.Locadora.Model.Fabricante;
import com.Locadora.Model.Modelo;
import com.Locadora.Repository.CarroRepository;
import com.Locadora.Repository.FabricanteRepository;
import com.Locadora.Repository.ModeloRepository;

@Controller
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Carro>> getAllCarros() {
        List<Carro> carros = (List<Carro>) carroRepository.findAll();
        return ResponseEntity.ok(carros);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Carro> getCarroById(@PathVariable(value = "id") Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Carro> cadastrarCarro(@RequestBody Carro novoCarro) {
        Optional<Fabricante> fabricanteOptional = Optional.empty();
        Optional<Modelo> modeloOptional = modeloRepository.findById(novoCarro.getModeloId());

        if (fabricanteOptional.isPresent() && modeloOptional.isPresent()) {
            // Verifica se a placa não é nula ou vazia
            if (novoCarro.getPlaca() == null || novoCarro.getPlaca().isEmpty()) {
                // Trate aqui o caso em que a placa é nula ou vazia
                // Por exemplo, lançando uma exceção ou definindo uma placa padrão
                return ResponseEntity.badRequest().build();
            }

            Carro carroSalvo = carroRepository.save(novoCarro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
        } else {
            return ResponseEntity.badRequest().build(); // Fabricante ou Modelo não encontrado
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> atualizarCarro(@PathVariable("id") Long id, @RequestBody Carro carro) {
        Optional<Carro> carroExistente = carroRepository.findById(id);
        if (carroExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Carro carroAtualizado = carroExistente.get();
        BeanUtils.copyProperties(carro, carroAtualizado, "id"); // Atualiza apenas os campos fornecidos
        carroAtualizado = carroRepository.save(carroAtualizado);
        return ResponseEntity.ok(carroAtualizado);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deletarCarro(@PathVariable("id") Long id) {
        if (!carroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        carroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
