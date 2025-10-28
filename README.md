Sistema de Reserva de Hotel

Integrante: RM553282 Kaio Vinicius Meireles Alves

Este projeto foi desenvolvido para a disciplina de Arquitetura Orientada a Serviço, com foco em boas práticas, arquitetura em camadas e APIs REST. É um sistema de reservas de hotel que permite cadastrar quartos, criar reservas, fazer check-in e check-out, cancelar reservas e validar regras de negócio importantes, como disponibilidade de quartos e datas corretas.

A ideia principal foi construir uma aplicação organizada, funcional e fácil de entender, mostrando separação de responsabilidades e tratamento consistente de erros.

Descrição do Projeto

O sistema permite que o usuário cadastre quartos informando número, tipo, capacidade, valor da diária e status. Também é possível criar reservas associadas a um quarto, fazer check-in, check-out e cancelar reservas. Ao realizar o check-out, o sistema calcula automaticamente o valor total da estadia.

A aplicação foi desenvolvida em Java usando Spring Boot, banco H2 em memória e Flyway para gerenciar a criação e migração do banco de dados. A arquitetura segue o padrão MVC, separando claramente responsabilidades entre Controller, Service e Repository, além de ter tratamento global de exceções.

Estrutura do Projeto

O projeto está organizado em três camadas principais:

Controller: responsável por receber requisições HTTP e enviar respostas REST.
Service: contém as regras de negócio, garantindo validações e lógica central do sistema.
Repository: gerencia a persistência dos dados no banco.

As entidades principais são Room e Reservation. O sistema valida a disponibilidade dos quartos e o estado das reservas antes de qualquer operação, garantindo que não haja conflitos.

Banco de Dados

O banco utilizado é o H2, em memória, o que facilita testes e execução local. A estrutura básica das tabelas é:

Room: id, número (único), tipo (STANDARD, DELUXE, SUITE), capacidade, valor da diária e status (ATIVO/INATIVO).
Reservation: id, quarto associado, nome do hóspede, datas de check-in e check-out previstas, status (CREATED, CHECKED_IN, CHECKED_OUT, CANCELED) e valor total calculado no check-out.

As tabelas são criadas automaticamente com Flyway, permitindo que a aplicação seja iniciada sem necessidade de scripts manuais.

Endpoints da API

Room
POST /rooms: cadastra um novo quarto
GET /rooms: lista todos os quartos

Reservation
POST /reservations: cria uma nova reserva
POST /reservations/{id}/checkin: realiza check-in da reserva
POST /reservations/{id}/checkout: realiza check-out e calcula o valor total
POST /reservations/{id}/cancel: cancela a reserva

Todos os endpoints retornam mensagens claras em caso de erro, como tentativa de reservar um quarto já ocupado ou informar datas inválidas.

Como Executar

Para rodar o projeto, basta clonar o repositório e usar o Maven.
No terminal, dentro da pasta do projeto, rode:

mvn clean install
mvn spring-boot:run


O H2 Console pode ser acessado em http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:hotel_db
Username: sa
Password: vazio

Depois disso, você pode testar os endpoints usando Postman, Insomnia ou qualquer cliente HTTP.
