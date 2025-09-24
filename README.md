# 🚗 Locadora Brass

Sistema de gerenciamento de **locadora de veículos** com **backend em Java + JPA** e **frontend em HTML, CSS, JavaScript (jQuery e Bootstrap)**.  
O projeto permite o cadastro de clientes e veículos, listagem, seleção e realização de novos aluguéis com cálculo automático de valor.

---

## 📌 Funcionalidades

### 🔹 Backend (Java + JPA/Hibernate)
- **Entidade Cliente** (`Cliente.java`)
  - Campos: `id`, `nome`, `cpfCnpj`, `dataNascimento`, `endereco`.
- **Entidade Veículo** (`Veiculo.java`)
  - Campos: `id`, `modelo`, `placa`, `tipo`, `ano`, `status`, `diaria`.
- **Entidade Aluguel** (`Aluguel.java`)
  - Relacionamentos: `Cliente` (ManyToOne), `Veiculo` (ManyToOne).
  - Campos: `dias`, `valorTotal`.

### 🔹 Frontend (HTML + JS + Bootstrap)
- **index.html** → Menu principal com navegação entre as telas.
- **cadastro_cliente.html** → Cadastro de cliente com validação e persistência em `localStorage`.
- **cadastro_veiculo.html** → Cadastro de veículo com validação e persistência em `localStorage`.
- **listar_clientes.html** → Listagem de clientes cadastrados e seleção para aluguel.
- **listar_veiculos.html** → Listagem de veículos cadastrados e seleção para aluguel.
- **aluguel.html** → Registro de novo aluguel com cálculo automático do valor total.

---

## 📂 Estrutura de Arquivos

/locadora-brass
│── backend/
│ └── com/locadora/model/
│ ├── Cliente.java
│ ├── Veiculo.java
│ └── Aluguel.java
│
│── frontend/
│ ├── index.html
│ ├── cadastro_cliente.html
│ ├── cadastro_veiculo.html
│ ├── listar_clientes.html
│ ├── listar_veiculos.html
│ ├── aluguel.html
│ ├── style.css



---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 21+**
- **Jakarta Persistence (JPA)**
- **Hibernate**
- **MySQL** (ou outro banco relacional configurado)
- **Maven**

### Frontend
- **HTML5**
- **CSS3**
- **Bootstrap 5.3**
- **JavaScript ES6**
- **jQuery 3.7**
- **LocalStorage + Cookies**
🔹 Frontend
Abra o arquivo index.html em um navegador moderno.

Navegue entre as telas utilizando os botões do menu principal.

Cadastre clientes, veículos e realize novos aluguéis.

✅ Validações
Todos os campos de formulários são obrigatórios.

O CPF/CNPJ é validado como campo de texto obrigatório.

A diária deve ser maior que zero.

O número de dias de aluguel deve ser >= 1.

O valor total do aluguel é calculado automaticamente.

📸 Fluxo do Sistema
Cadastro do Cliente → Preenchimento de dados pessoais.

Cadastro do Veículo → Registro de modelo, placa, tipo, ano, diária e status.

Listagem e Seleção → Escolha do cliente e veículo.

Novo Aluguel → Informar número de dias → Cálculo automático do valor total → Confirmação.

📌 Melhorias Futuras
Persistência definitiva em banco (CRUD completo no backend).

Upload de documentos e imagens de veículos.

Autenticação de usuários.

Relatórios de aluguéis em PDF.

📄 Licença
Este projeto é livre para fins acadêmicos e de estudo.







   


