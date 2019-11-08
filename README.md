# Componente Backend para prueba



## Requisitos

Maven o IDE Java para inicializar Spring App en Compilador

URL y Credenciales de una Base de datos en MySQL

### Modificar de ser necesario

#### src\main\resources\application.properties  


- server.port: Puerto (default 8080)

- spring.datasource.url: url de servidor mysql (default localhost:3306)

- spring.datasource.username: usuario mysql (default root)

- spring.datasource.password: contraseña mysql (default root)

- URL: Url de Api Externa ( solo se modifica si el host cambia)



 
```

## Uso

mvn spring-boot:run

```

## Notas

- No necesita script .sql para crear base de datos y/o tablas. Se creara bd ingtest por default
