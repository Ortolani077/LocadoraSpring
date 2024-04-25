package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Model.Modelo;
import Repository.ModeloRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modelos")
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
    public ResponseEntity deleteModelo(@PathVariable("id") Long id) {
        modeloRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
