## Descripción:
Sistema de gestión de materiales con una API RESTful en Java (Spring Boot). La API incluye autenticación JWT, documentación con Swagger y una base de datos en MySQL. La interfaz permite la gestión y búsqueda de materiales por tipo, ciudad y fecha de compra.

### Tecnologías utilizadas:

- Java 17+
- Spring Boot
- JPA
- MySQL
- JWT
- Swagger

### Requisitos

- **Java 17**
- **Maven**
- **MySQL**

### Pasos para iniciar el proyecto

1. **Clonar el repositorio**

   git clone https://github.com/Jorgeehz/prueba_tecnica.git

2. **Configura la base de datos**

   Importa el archivo prueba_tecnica.sql en MySQL

3. **Compilar el proyecto**

   Ejecuta mvn clean install
 
4. **Inicia el servidor**

   Ejecuta mvn spring-boot:run

5. **Accede a la documentación de la API con Swagger**

   Copia y pega esta URL en tu navegador http://localhost:8080/swagger-ui/
