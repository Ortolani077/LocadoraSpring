package com.Locadora.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Locadora.Model.Fabricante;
import com.Locadora.Repository.FabricanteRepository;

@RestController
@RequestMapping("/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Fabricante>> getAllFabricantes() {
        List<Fabricante> fabricantes = (List<Fabricante>) fabricanteRepository.findAll();
        return new ResponseEntity<>(fabricantes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Fabricante> getFabricanteById(@PathVariable(value = "id") Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id).orElse(null);
        if (fabricante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fabricante, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Fabricante> cadastrarFabricante(@RequestBody Fabricante fabricante) {
        try {
            if (fabricante == null) {
                return ResponseEntity.badRequest().build(); // Retornar Bad Request se fabricante for nulo
            }
            Fabricante novoFabricante = fabricanteRepository.save(fabricante);
            return new ResponseEntity<>(novoFabricante, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            // Logar a exceção e retornar um erro interno do servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Fabricante> atualizarFabricante(@PathVariable("id") Long id, @RequestBody Fabricante fabricante) {
        try {
            Fabricante fabricanteExistente = fabricanteRepository.findById(id).orElse(null);
            if (fabricanteExistente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            fabricante.setId(id); // Garanta que o ID do fabricante seja definido corretamente
            Fabricante novoFabricante = fabricanteRepository.save(fabricante);
            return new ResponseEntity<>(novoFabricante, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping(value = "/{id}", produces = "application/text")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            fabricanteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir fabricante.");
        }
    }
}
