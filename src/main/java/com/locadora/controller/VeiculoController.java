
package com.locadora.controller;

import com.locadora.model.Veiculo;
import com.locadora.repository.VeiculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {
    private final VeiculoRepository repo;
    public VeiculoController(VeiculoRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Veiculo> listar(){ return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Veiculo> criar(@RequestBody Veiculo v){
        if (v.getStatus() == null) v.setStatus("Disponível");
        Veiculo saved = repo.save(v);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody Veiculo novo){
        return repo.findById(id).map(v -> {
            v.setModelo(novo.getModelo());
            v.setPlaca(novo.getPlaca());
            v.setTipo(novo.getTipo());
            v.setAno(novo.getAno());
            v.setDiaria(novo.getDiaria());
            v.setStatus(novo.getStatus());
            repo.save(v);
            return ResponseEntity.ok(v);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if (repo.existsById(id)) { repo.deleteById(id); return ResponseEntity.noContent().build();}
        return ResponseEntity.notFound().build();
    }
}
