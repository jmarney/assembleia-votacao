# Autor

- João Marney Ramos Costa
- E-mail: marneycosta@gmail.com

# assembleia-votacao

API que gerencia sessões de votos em Assembleias

### Tech

* Java 8
* SpringBoot Web / JPA / Swagger 2
* Lombok
* Junit 4
* H2 Database

# Requisitos

- Instalar e configurar:  
    - JDK 8
    - Maven
    - H2 Database
        - Criar banco e alterar em src/main/resources/application.properties a variavel spring.datasource.url conforme banco criado.

### Executando

 - Ambiente local
```sh
mvn clean package spring-boot:run
```
- Buildar
```sh
mvn clean package
```

#### Swagger

local:
```
http://localhost:8080/swagger-ui.html#/
```
Produção:
```
https://assembleiavota.herokuapp.com/swagger-ui.html/#/
```