package com.locadora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // Página inicial / menu
    @GetMapping("/")
    public String index() {
        return "index"; // index.html em templates
    }

    // Cadastro de cliente
    @GetMapping("/cadastro-cliente")
    public String cadastroCliente() {
        return "cadastro_cliente"; // cadastro_cliente.html
    }

    // Cadastro de veículo
    @GetMapping("/cadastro-veiculo")
    public String cadastroVeiculo() {
        return "cadastro_veiculo"; // cadastro_veiculo.html
    }

    // Realizar aluguel
    @GetMapping("/aluguel")
    public String aluguel() {
        return "aluguel"; // aluguel.html
    }

    // Listagem de veículos
    @GetMapping("/veiculos")
    public String veiculos() {
        return "veiculos"; // veiculos.html
    }
}
