package com.locadora.config;

import com.locadora.model.Cliente;
import com.locadora.model.Veiculo;
import com.locadora.repository.ClienteRepository;
import com.locadora.repository.VeiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(ClienteRepository clienteRepo, VeiculoRepository veiculoRepo){
        return args -> {
            if (clienteRepo.count() == 0){
                Cliente c = new Cliente();
                c.setNome("João Silva");
                c.setCpfCnpj("111.222.333-44");
                c.setDataNascimento(LocalDate.of(1990,1,1));
                c.setEndereco("Rua A, 123");
                clienteRepo.save(c);
            }
            if (veiculoRepo.count() == 0){
                Veiculo v = new Veiculo();
                v.setModelo("Fiat Uno");
                v.setPlaca("ABC-1234");
                v.setTipo("Compacto");
                v.setAno(2015);
                v.setDiaria(BigDecimal.valueOf(120.00));
                v.setStatus("Disponível");
                veiculoRepo.save(v);
            }
        };
    }
}
