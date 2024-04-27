package com.Locadora.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.Locadora.Model.Fabricante;
import com.Locadora.Repository.FabricanteRepository;

@Controller
@RequestMapping(method = RequestMethod.GET, value = "/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> getFabricanteById(@PathVariable Long id) {
        Fabricante fabricante = fabricanteRepository.findById(id).orElse(null);
        if (fabricante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fabricante, HttpStatus.OK);
    }

   

    
    
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Fabricante> cadastrarFabricante(@RequestBody Fabricante fabricante) {
        try {
            Fabricante novoFabricante = fabricanteRepository.save(fabricante);
            return new ResponseEntity<>(novoFabricante, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Fabricante> atualizarFabricante(@PathVariable Long id, @RequestBody Fabricante fabricante) {
        try {
            Fabricante fabricanteExistente = fabricanteRepository.findById(id).orElse(null);
            if (fabricanteExistente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            fabricante.setId(id); // Definindo o ID do fabricante
            Fabricante novoFabricante = fabricanteRepository.save(fabricante);
            return new ResponseEntity<>(novoFabricante, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            fabricanteRepository.deleteById(id);
            return ResponseEntity.ok("Fabricante excluído com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir fabricante");
        }
    }
}
