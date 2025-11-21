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

## Requisitos para executar

Ter instalado:
- Java 21 ou superior
- Maven
- Docker

## Como executar o projeto

1. Gere o .jar:

```sh
mvn clean package -DskipTests
```

Isso criará o arquivo: target/(nome-do-app).jar

2. Suba os containers:

```sh
docker-compose up -d
```

3. Para derrubar os containers:

```sh
docker-compose down
```

No primeiro build da API, o banco é gerado apenas com o usuário Administrador, suas credenciais são:
- login: admin@sistema.com
- senha: admin

A API estará disponível em http://localhost:8080. Para acessar a documentação da API, acesse http://localhost:8080/swagger-ui/index.html.

O frontend está disponível no repositório: https://github.com/brunoogalvani/sistema-ponto-frontend