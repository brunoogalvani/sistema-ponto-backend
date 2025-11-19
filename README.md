# Sistema de Ponto – Backend

Essa API foi desenvolvida como projeto pessoal para estudar a linguagem **Java**, seu framework **Spring Boot**, o banco de dados **PostgreSQL** e o **Docker** para containerização.

## Tecnologias

- Java
- Spring Boot
- PostgreSQL
- Docker

## Funcionalidades

- Listar, adicionar, editar ou remover usuários
- Listar, registrar ou solicitação alteração de pontos
- Listar e processar solicitações
- Visualização de administrador do sistema

## Como executar o projeto

1. Suba os containers:

```sh
docker-compose up -d
```

2. Para derrubar os containers:

```sh
docker-compose down
```

A API estará disponível em http://localhost:8080. Para acessar a documentação da API, acesse http://localhost:8080/api-docs.

O frontend está disponível no repositório: https://github.com/brunoogalvani/sistema-ponto-frontend