
package com.locadora.controller;

import com.locadora.model.Cliente;
import com.locadora.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    private final ClienteRepository repo;
    public ClienteController(ClienteRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Cliente> listar(){ return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente){
        Cliente saved = repo.save(cliente);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        return repo.findById(id).map(c -> {
            c.setNome(cliente.getNome());
            c.setCpfCnpj(cliente.getCpfCnpj());
            c.setDataNascimento(cliente.getDataNascimento());
            c.setEndereco(cliente.getEndereco());
            repo.save(c);
            return ResponseEntity.ok(c);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(repo.existsById(id)){ repo.deleteById(id); return ResponseEntity.noContent().build(); }
        return ResponseEntity.notFound().build();
    }
}
