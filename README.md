# Backend

Este es un backend / Maven / Spring Boot (versión 2.7.15) aplicación que es un microservicio que contiene un REST API que responde a las necesidades de los frontend del software.

## Definiendo parámetros de aplicación

### Base de datos

Este proyecto usa un servicio de datos MySQL, donde para definir el tipo de base de datos especifico (Postgres, MySQL Server, etc) es necesario modificar el archivo pom.xml y añadir la dependencia requerida para su conexión.

Para el caso de MySQL Server se tiene lo siguiente:

```
        <dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
```

Una vez hecho esto, se pueden definir los parámetros de conexión a la base de datos en el archivo ```src/main/resources/application.properties```. El archivo debe lucir así:

```
spring.datasource.url={{url del servicio de base de datos}}
misdevsc_amacom?sessionVariables=sql_mode=''
spring.datasource.username={{nombre de usuario para acceder a la base de datos}}
spring.datasource.password={{contraseña para acceder a la base de datos}}

server.servlet.context-path=/api
spring.jpa.show-sql=true

#spring.datasource.driver-class-name=org.mariadb.jdbc.Driver

# ENABLE SQL DDL AUTO FOR DATABASE MIGRATIONS
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
server.error.include-stacktrace=never

spring.datasource.hikari.pool-name=HikariPoolMariaDB
```
### Credenciales para envió de correos electrónicos

Para el envío de correos electrónicos usado en diferentes módulos de la aplicación se deben definir los parámetros de conexión al servicio de correo en el archivo ```src/main/resources/application.properties```. El archivo debe lucir así:

```
spring.mail.host={{smtp usado por el servicio}}
spring.mail.port={{puerto para el servicio de smtp}}
spring.mail.username={{nombre de usuario (correo)}}
spring.mail.password={{contraseña}}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true

spring.mvc.pathmatch.matching-strategy=ant_path_matcher
```

## Cómo ejecutar 

Esta aplicación está empaquetada como un jar. Se ejecuta usando el comando ```java -jar``.

* Clona este repositorio 
* Asegúrate de que estás usando Java 11 y Maven 3.x
* Puedes construir el proyecto y ejecutar las pruebas ejecutando ``mvn clean package``.
* Una vez construido con éxito, puede ejecutar el servicio por uno de estos dos métodos:

```bash
   $  java -jar -Dspring.config.location=src/main/resources/application.properties {{ la ruta hacía el archivo jar generado}}
```
o

```bash
   $  mvn spring-boot:run -Drun.arguments="spring.config.location=src/main/resources/application.properties"
```

Una vez que la aplicación se ejecuta debería ver algo como esto

```
2024-02-28 14:05:06.747  INFO 14568 --- [  restartedMain] .d.s.w.r.o.CachingOperationNameGenerator : Generating unique operation named: updateUsingPUT_34  
2024-02-28 14:05:06.760  INFO 14568 --- [  restartedMain] .d.s.w.r.o.CachingOperationNameGenerator : Generating unique operation named: findPageableUsingGET_24
2024-02-28 14:05:06.884  INFO 14568 --- [  restartedMain] com.amacom.amacom.AmacomApplication      : Started AmacomApplication in 30.54 seconds (JVM running for 31.534)
```

## Acerca del servicio

El servicio es un simple servicio REST de revisión de hoteles. Utiliza una base de datos relacional como MySQL o PostgreSQL. Si tus propiedades de conexión a la base de datos funcionan, puedes llamar a algunos endpoints REST definidos en ```com.amacom.amacom``` en el **puerto 8080** bajo la ruta  ```/api```.

De forma detallada se tiene la documentación de cada ruta y subruta junto con información de cada endpoint en la siguiente ruta:
```
{{url del servicio backend}}/api/swagger-ui.html
```
