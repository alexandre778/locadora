package com.locadora.service;

import com.locadora.model.Veiculo;
import com.locadora.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository){
        this.repository = repository;
    }

    public Veiculo salvar(Veiculo veiculo){
        return repository.save(veiculo);
    }

    public List<Veiculo> listarTodos(){
        return repository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id){
        return repository.findById(id);
    }
}
