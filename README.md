# Spring Boot Odyssey With Kotlin + JDBC + H2

This service exposes a Blog APIs build using Spring Boot + Kotlin + JPA (Hibernate) + Derby

## Requirements

1. Java - >= 1.8

2. H2 

3. Spring Boot 2.0.2.RELEASE

4. Kotlin - 1.2.41

## Instruction to setup

#####1. Clone the application

```bash
git@github.com:mgorav/SpringKotlinJdbc.git
```


#####2. Running the App

Use following command to run the application -

```bash
mvn spring-boot:run
```

All the APIs exposed can be viewed using swagger UI

```
http://localhost:8080/swagger-ui.html
```

##### Glossary of all the APIs exposed



    GET /api/blogs
    
    POST /api/blogs
    
    GET /api/blogs/{id}
    

