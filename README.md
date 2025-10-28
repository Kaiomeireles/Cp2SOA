# Sistema de Reserva de Hotel

**Integrante:** RM553282 Kaio Vinicius Meireles Alves

---

## Descrição do Sistema

O sistema permite cadastrar quartos com número, tipo, capacidade, valor da diária e status. É possível criar reservas para esses quartos, realizar check-in, check-out e cancelamento de reservas. No check-out, o sistema calcula automaticamente o valor total da estadia.

As principais regras de negócio implementadas são:

* Verificar se o quarto está disponível nas datas solicitadas.
* Garantir que datas de check-in e check-out estejam corretas.
* Atualizar o status das reservas de forma consistente (CREATED, CHECKED_IN, CHECKED_OUT, CANCELED).

O sistema foi desenvolvido em **Java com Spring Boot**, utilizando **MVC** para organizar o código em Controller, Service e Repository, e **Flyway** para gerenciar a migração do banco H2 em memória.

---

## Arquitetura e Organização

* **Controller:** recebe as requisições HTTP e envia respostas REST.
* **Service:** contém as regras de negócio e validações, garantindo que apenas operações válidas sejam executadas.
* **Repository:** gerencia o acesso ao banco de dados, usando JPA.
* **Model:** representa as entidades do sistema (`Room` e `Reservation`).
* **Exception:** tratamento global de erros para respostas consistentes.

Essa organização garante que o sistema seja fácil de manter e expandir, seguindo boas práticas de arquitetura.

---

## Banco de Dados

O sistema usa **H2 em memória** para facilitar testes locais, com tabelas criadas automaticamente via **Flyway**.

**Tabela Room**
`id, número (único), tipo (STANDARD, DELUXE, SUITE), capacidade, valor da diária, status (ATIVO / INATIVO)`

**Tabela Reservation**
`id, quarto associado, nome do hóspede, check-in, check-out, status (CREATED, CHECKED_IN, CHECKED_OUT, CANCELED), valor total`

O Flyway garante que o banco seja criado e versionado automaticamente, sem necessidade de scripts manuais.

---

## Funcionalidades Implementadas

* Cadastro e listagem de quartos
* Criação de reservas com validação de disponibilidade
* Check-in e check-out de reservas
* Cancelamento de reservas
* Cálculo automático do valor total no check-out
* Tratamento global de erros com mensagens consistentes
* Banco versionado com Flyway para facilitar manutenção e evolução

---

## Endpoints da API

**Quarto**

* `POST /rooms` – cadastrar novo quarto
* `GET /rooms` – listar todos os quartos

**Reserva**

* `POST /reservations` – criar reserva
* `POST /reservations/{id}/checkin` – check-in da reserva
* `POST /reservations/{id}/checkout` – check-out da reserva e cálculo do valor
* `POST /reservations/{id}/cancel` – cancelar reserva

Todos os endpoints retornam respostas claras em caso de erro, como quando um quarto já está reservado nas datas solicitadas.

---

## Como Executar

Clonar o repositório:

```bash
git clone <seu-repositorio>
cd hotel-reservation
```

Rodar o projeto usando Maven:

```bash
mvn clean install
mvn spring-boot:run
```

Acessar o H2 Console (opcional) para ver o banco de dados:

```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:hotel_db
Username: sa
Password: vazio
```

Testar os endpoints via **Postman**, **Insomnia** ou **curl**.

---

## Considerações Finais

O projeto cumpre todos os requisitos do desafio, aplicando arquitetura em camadas, boas práticas REST, validação de regras de negócio, tratamento de erros e migração de banco versionada. É fácil de manter, expandir e serve como base sólida para evoluções futuras, como autenticação, integração com front-end ou notificações.

---

