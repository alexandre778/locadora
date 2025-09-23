
package com.locadora.controller;

import com.locadora.dto.AluguelRequest;
import com.locadora.model.Aluguel;
import com.locadora.service.AluguelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/alugueis")
@CrossOrigin(origins = "*")
public class AluguelController {
    private final AluguelService service;
    public AluguelController(AluguelService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody AluguelRequest req){
        try {
            Aluguel criado = service.criarAluguel(req);
            return ResponseEntity.ok(criado);
        } catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e){
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
