# PostContenido 2 - Unidad 8

## Persistencia con JPA/Hibernate - Relación ManyToMany

### Autor

Camilo Sánchez

### Descripción del Proyecto

Este proyecto corresponde al laboratorio PostContenido 2 de la Unidad 8 de Programación Web. Su objetivo es implementar una relación bidireccional **@ManyToMany** entre las entidades **Estudiante** y **Curso** utilizando Spring Boot, Spring Data JPA, Hibernate y MySQL.

La aplicación permite:

* Crear estudiantes.
* Crear cursos.
* Inscribir estudiantes en cursos.
* Desinscribir estudiantes de cursos.
* Consultar los cursos con sus estudiantes asociados.
* Gestionar la relación mediante una tabla intermedia generada automáticamente por Hibernate.
---

## Modelo Entidad-Relación

Relación ManyToMany entre Estudiante y Curso.

```text
+------------------+
|   ESTUDIANTE     |
+------------------+
| id               |
| nombre           |
| email            |
+------------------+
          |
          | N
          |
          |
          | M
+------------------+
| CURSO_ESTUDIANTE |
+------------------+
| estudiante_id FK |
| curso_id FK      |
+------------------+
          |
          | N
          |
          |
          | M
+------------------+
|      CURSO       |
+------------------+
| id               |
| nombre           |
| creditos         |
+------------------+
```

---

## Estructura del Proyecto

```text
src
 └── main
     ├── java
     │   └── com.universidad.estudiantes
     │       ├── controller
     │       ├── model
     │       ├── repository
     │       └── service
     └── resources
         ├── templates
         └── application.properties
```

---

## Configuración de la Base de Datos

Crear la base de datos:

```sql
CREATE DATABASE estudiantes_db;
```

Configurar el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/estudiantes_db
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## Ejecución del Proyecto

1. Clonar el repositorio:

```bash
git clone https://github.com/usuario/apellido-post2-u8.git
```

2. Ingresar al proyecto:

```bash
cd apellido-post2-u8
```

3. Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

4. Abrir en el navegador:

```text
http://localhost:8080
```

---
## Conclusión
Se implementó correctamente una relación ManyToMany entre las entidades Estudiante y Curso utilizando JPA/Hibernate. Además, se gestionó la sincronización bidireccional mediante métodos helper y se optimizó la carga de datos usando JOIN FETCH para evitar problemas de rendimiento asociados al patrón N+1.
