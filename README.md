# Solicitações

### 🚀 Começando
Aplicação Rest para atendimento de Solicitações.

## Principais Tecnologias
- [Spring Boot]
- [Spring Web]
- [Spring Data]
- [Hibernate]
- [Swagger]
- [Mockito]

# Persistência
Neste projeto é utilizando um Banco de Dados Relacional H2, tanto para a Aplicação como para os Testes de Integração

# Testes
## Unitários
Os testes unitários foram implementados utilizando JUnit
## Integração
Os testes de integração foram implementados utilizando Mockito e persistência em H2 

# Setup da aplicação (local)
### 📋 Pré-requisitos
Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
- Java 11
- Maven 3.1.0

## ⚙️ Instalação da aplicação
Primeiramente, faça o clone do repositório:
```
https://github.com/alex-girao/solicitacoes.git
```
Feito isso, acesse o projeto:
```
cd solicitacoes
```
É preciso compilar o código e baixar as dependências do projeto:
```
mvn clean package
```
Finalizado esse passo, vamos iniciar a aplicação:
```
mvn spring-boot:run
```
Pronto. A aplicação está disponível em http://localhost:8080
```
Tomcat started on port(s): 8080 (http)
Started AppConfig in xxxx seconds (JVM running for xxxx)
```

# Endpoints
O projeto disponibiliza endpoints para: Criação, Consulta e Finalização de Solicitações, onde utilizam o padrão Rest de comunicação, produzindo e consumindo dados no formato JSON.

A documentação Swagger está disponível em http://localhost:8080/swagger-ui.html

## Solicitações
#### Listar Todos
GET /solicitacao
```bash
http://localhost:8080/solicitacao/
```
#### Listar por Id
GET /solicitacao/:id
```bash
http://localhost:8080/solicitacao/1
```
#### Finalizar
PATCH /solicitacao/:id
```bash
http://localhost:8080/solicitacao/1
```
#### Criar
POST /solicitacao
```bash
http://localhost:8080/solicitacao/
```
Request Body
```bash
{
	"texto": "Cartão bloqueado",
	"idTipoSolicitacao": 1
}
```

## Tipos de Solicitações
#### Listar Todos
GET /tipoSolicitacao
```bash
http://localhost:8080/tipoSolicitacao/
```