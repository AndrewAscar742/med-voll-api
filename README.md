
# Med Voll API

API desenvolvida para um projeto educacional da Alura Med Voll, uma
clínica médica que realiza a gestão em Médicos, Pacientes e Usuários


## Stack utilizada

**Back-end:** Spring Boot, Spring Data JPA, Spring Security, JWT Lombok, Flyway, Records, MySQL e Git


## Funcionalidades

- Visualizar e Manipular (CRUD) Usuários, Pacientes e Médicos
- As senhas são salvas criptografadas 
- Autenticação de usuário por JWT (Auth0)
- Autenticação de usuário necessária para utilizar os outros endpoints



## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/AndrewAscar742/med-voll-api.git
```

Importe o projeto para sua IDE que contenha o STS

```bash
  Foi utilizado o Spring Tool Suite com Eclipse
```

Inicie o MySQL com a conexão ativa

```bash
  Para realizar a conexão, é necessário ter o XAMPP ativo
```

Inicie o Projeto!

```bash
  Por padrão, está rodando na 8080
```

## Documentação da API

#### Retorna uma chave JWT caso o usuário seja logado
Primeira requisição que deve ser feita para

```http
  POST /login
```
Body: {

    "login": "exemplo@voll.med",
    "senha": "123456" 
}    
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `null` | `string` | **Obrigatório:**. Body |

#### Retorna médicos em forma de paginação

```http
  GET /medicos
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `null`      | `string` | Retorna médicos com suas descrições|

#### Retorna um médico pelo ID

```http
  GET /medicos/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `integer` | Retorna um médico com suas descrições|

#### Realiza o cadastramento de um médico

```http
  POST /medicos
```

Body: {

    "nome": "Zé Carlos",
    "email": "ZeCarlos@voll.med",
    "telefone": "11999999345",
    "crm": "423156",
    "especialidade": "DERMATOLOGIA",
    "endereco": {
        "logradouro": "rua 1",
        "bairro": "bairro",
        "cep": "12345678",
        "cidade": "Brasilia",
        "uf": "DF",
        "numero": "1",
        "complemento": "complemento"
    } 
}

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `null` | `string` | **Obrigatório:**. Body |

#### Realiza alterações no médico escolhido pelo ID

```http
  PUT /medicos
```

Body:{

    "id": 1,
    "nome": "Exemplo"
    "telefone": "11999999999"
    "endereco": {
        "logradouro": "rua 1",
        "bairro": "bairro",
        "cep": "12345678",
        "cidade": "Brasilia",
        "uf": "DF",
        "numero": "1",
        "complemento": "complemento"
    } 
}

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `integer` | **Obrigatório:**. ID e os campos de endereço |

#### Desativa um Médico

```http
  DELETE /medicos/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `integer` | **Obrigatório:**. id |


## Autores

- [@AndrewAscar742](https://www.github.com/AndrewAscar742)


## Aprendizados

- Uso do Spring Security
- Entendimento básico sobre JWT
- Uso de Records como DTOS para evitar classes Java Beans
- Mapeamento de Entidades
- Uso do BCrypit para criptografia
- Diferença entre Componente, Repository e Service
- Paginação e Ordenação dos elementos devolvidos na resposta

