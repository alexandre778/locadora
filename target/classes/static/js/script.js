$(document).ready(function(){

    /** ================= MENU PRINCIPAL ================= **/
    $('#btnCliente').click(function(){
        window.location.href = 'cadastro_cliente.html';
    });

    $('#btnVeiculo').click(function(){
        window.location.href = 'cadastro_veiculo.html';
    });

    $('#btnListarClientes').click(function(){
        window.location.href = 'listar_clientes.html';
    });

    $('#btnListarVeiculos').click(function(){
        window.location.href = 'listar_veiculos.html';
    });

    $('#btnAluguel').click(function(){
        // Redireciona para o aluguel ou seleção
        window.location.href = 'aluguel.html';
    });

    $('#btnSair').click(function(){
        if(confirm("Deseja realmente sair?")){
            window.location.href = 'index.html'; // ou página de login
        }
    });

    /** ================= CADASTRO DE CLIENTE ================= **/
    if($('#formCliente').length){
        $('#formCliente').submit(function(e){
            e.preventDefault();
            let nome = $('#nome').val().trim();
            let cpfCnpj = $('#cpfCnpj').val().trim();
            let dataNascimento = $('#dataNascimento').val().trim();
            let endereco = $('#endereco').val().trim();

            if(!nome || !cpfCnpj || !dataNascimento || !endereco){
                alert("Preencha todos os campos.");
                return;
            }

            let cliente = {nome, cpfCnpj, dataNascimento, endereco};
            localStorage.setItem('cliente', JSON.stringify(cliente));
            setCookie('clienteSelecionado', nome, 1);
            alert("Cliente cadastrado com sucesso!");
            $('#formCliente')[0].reset();
        });
    }

    /** ================= CADASTRO DE VEÍCULO ================= **/
    if($('#formVeiculo').length){
        $('#formVeiculo').submit(function(e){
            e.preventDefault();
            let modelo = $('#modelo').val().trim();
            let placa = $('#placa').val().trim();
            let tipo = $('#tipo').val().trim();
            let ano = $('#ano').val().trim();
            let diaria = $('#diaria').val().trim();
            let status = $('#status').val().trim();

            if(!modelo || !placa || !tipo || !ano || !diaria || !status){
                alert("Preencha todos os campos.");
                return;
            }

            let veiculo = {modelo, placa, tipo, ano, diaria, status};
            localStorage.setItem('veiculo', JSON.stringify(veiculo));
            setCookie('veiculoSelecionado', modelo, 1);
            alert("Veículo cadastrado com sucesso!");
            $('#formVeiculo')[0].reset();
        });
    }

    /** ================= LISTAR CLIENTES ================= **/
    if($('#tabelaClientes tbody').length){
        let cliente = JSON.parse(localStorage.getItem('cliente'));
        if(cliente){
            let row = `<tr>
                <td>${cliente.nome}</td>
                <td>${cliente.cpfCnpj}</td>
                <td>${cliente.dataNascimento}</td>
                <td>${cliente.endereco}</td>
            </tr>`;
            $('#tabelaClientes tbody').append(row);
        }

        $('#btnSelecionar').click(function(){
            if(cliente){
                alert("Cliente selecionado: " + cliente.nome);
                setCookie('clienteSelecionado', cliente.nome, 1);
                window.location.href = 'listar_veiculos.html';
            } else {
                alert("Nenhum cliente cadastrado.");
            }
        });
    }

    /** ================= LISTAR VEÍCULOS ================= **/
    if($('#tabelaVeiculos tbody').length){
        let veiculo = JSON.parse(localStorage.getItem('veiculo'));
        if(veiculo){
            let row = `<tr>
                <td>${veiculo.modelo}</td>
                <td>${veiculo.placa}</td>
                <td>${veiculo.tipo}</td>
                <td>${veiculo.ano}</td>
                <td>${veiculo.diaria}</td>
                <td>${veiculo.status}</td>
            </tr>`;
            $('#tabelaVeiculos tbody').append(row);
        }

        $('#btnSelecionarVeiculo').click(function(){
            if(veiculo){
                alert("Veículo selecionado: " + veiculo.modelo);
                setCookie('veiculoSelecionado', veiculo.modelo, 1);
                window.location.href = 'aluguel.html';
            } else {
                alert("Nenhum veículo cadastrado.");
            }
        });
    }

    /** ================= NOVO ALUGUEL ================= **/
    if($('#formAluguel').length){
        let clienteNome = getCookie('clienteSelecionado');
        let veiculoNome = getCookie('veiculoSelecionado');
        let veiculo = JSON.parse(localStorage.getItem('veiculo'));

        if(!clienteNome || !veiculoNome){
            alert("Selecione cliente e veículo antes de prosseguir.");
            window.location.href = 'index.html';
        } else {
            $('#clienteSelecionado').text(clienteNome);
            $('#veiculoSelecionado').text(veiculoNome);
        }

        $('#btnCalcular').click(function(){
            let dias = parseInt($('#dias').val());
            if(!dias || dias <= 0){
                alert("Digite um número válido de dias.");
                return;
            }
            let total = dias * parseFloat(veiculo.diaria);
            $('#valorTotal').text("R$ " + total.toFixed(2));
        });

        $('#formAluguel').submit(function(e){
            e.preventDefault();
            let dias = parseInt($('#dias').val());
            if(!dias || dias <= 0){
                alert("Digite um número válido de dias.");
                return;
            }
            let total = dias * parseFloat(veiculo.diaria);
            alert("Aluguel registrado com sucesso!\nValor total: R$ " + total.toFixed(2));
            $('#dias').val('');
            $('#valorTotal').text("R$ 0,00");
        });
    }

});

/** ================= FUNÇÕES AUXILIARES ================= **/
function setCookie(name, value, days=1){
    let expires = "";
    if(days){
        let date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

function getCookie(name){
    let cookies = document.cookie.split(';');
    for(let c of cookies){
        let [key, value] = c.trim().split('=');
        if(key === name) return value;
    }
    return null;
}
