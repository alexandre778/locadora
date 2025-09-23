package com.locadora.service;

import com.locadora.dto.AluguelRequest;
import com.locadora.model.Aluguel;
import com.locadora.model.Cliente;
import com.locadora.model.Veiculo;
import com.locadora.repository.AluguelRepository;
import com.locadora.repository.ClienteRepository;
import com.locadora.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class AluguelService {
    private final AluguelRepository aluguelRepo;
    private final ClienteRepository clienteRepo;
    private final VeiculoRepository veiculoRepo;

    public AluguelService(AluguelRepository aluguelRepo, ClienteRepository clienteRepo, VeiculoRepository veiculoRepo) {
        this.aluguelRepo = aluguelRepo;
        this.clienteRepo = clienteRepo;
        this.veiculoRepo = veiculoRepo;
    }

    @Transactional
    public Aluguel criarAluguel(AluguelRequest req){
        Cliente cliente = clienteRepo.findById(req.getClienteId())
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));

        Veiculo veiculo = veiculoRepo.findById(req.getVeiculoId())
                .orElseThrow(() -> new NoSuchElementException("Veículo não encontrado"));

        if (!"Disponível".equalsIgnoreCase(veiculo.getStatus())) {
            throw new IllegalStateException("Veículo não está disponível");
        }

        BigDecimal diaria = veiculo.getDiaria() != null ? veiculo.getDiaria() : BigDecimal.ZERO;
        BigDecimal total = diaria.multiply(BigDecimal.valueOf(req.getDias()));

        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);
        aluguel.setDias(req.getDias());
        aluguel.setValorTotal(total);

        // Marca veículo como alugado
        veiculo.setStatus("Alugado");
        veiculoRepo.save(veiculo);

        return aluguelRepo.save(aluguel);
    }
}
