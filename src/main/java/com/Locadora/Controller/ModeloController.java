package com.Locadora.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;

import com.Locadora.Model.Modelo;
import com.Locadora.Repository.ModeloRepository;

@Controller
@RequestMapping(method = RequestMethod.GET, value = "/modelos")
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRepository;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Modelo>> getAllModelos() {
        List<Modelo> modelos = (List<Modelo>) modeloRepository.findAll();
        return new ResponseEntity<>(modelos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Modelo> getModeloById(@PathVariable(value = "id") Long id) {
        Modelo modelo = modeloRepository.findById(id).orElse(null);
        if (modelo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(modelo, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Modelo> cadastrarModelo(@RequestBody Modelo modelo) {
        try {
            Modelo novoModelo = modeloRepository.save(modelo);
            return new ResponseEntity<>(novoModelo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Modelo> atualizarModelo(@PathVariable("id") Long id, @RequestBody Modelo modelo) {
        try {
            Modelo modeloExistente = modeloRepository.findById(id).orElse(null);
            if (modeloExistente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            modelo.setId(id); // Garanta que o ID do modelo seja definido corretamente
            Modelo novoModelo = modeloRepository.save(modelo);
            return new ResponseEntity<>(novoModelo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/text")
    public ResponseEntity<String> deleteModelo(@PathVariable("id") Long id) {
        try {
            modeloRepository.deleteById(id);
            return ResponseEntity.ok().body("Modelo excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir modelo");
        }
    }
}
