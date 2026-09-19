# reactivo
Proyecto de programación con spring boot y programación reactiva

Este proyecto de spring boot esta usando java 21 y webflux para crear una aplicación reactiva. La programación reactiva permite manejar flujos de datos de manera asíncrona y no bloqueante, lo que mejora el rendimiento y la escalabilidad de las aplicaciones.
Se conecta a base de datos de manera reactiva usando R2DBC, lo que permite realizar operaciones de base de datos de manera no bloqueante y eficiente.

Se implementa el concepto de DTO (Data Transfer Object) para transferir datos entre diferentes capas de la aplicación, lo que ayuda a mantener una separación clara de responsabilidades y facilita el mantenimiento del código.


Acceso a Swagger UI

Una vez iniciada la aplicación, las rutas por defecto accesibles desde el navegador son:

Interfaz gráfica (Swagger UI): http://localhost:8080/swagger-ui.html

Especificación JSON OpenAPI: http://localhost:8080/v3/api-docs

Este proyecto implementa migracion de base de datos a traves de Flyway, lo que permite versionar y gestionar los cambios en la estructura de la base de datos de manera controlada y reproducible.

Algo importante que se agrega a nivel de base de datos es la auditoria de base de datos que permite registrar y rastrear los cambios realizados en las tablas de la base de datos, lo que facilita el seguimiento de la información y la identificación de posibles problemas o inconsistencias
parte de esa auditoria se implementa gracias al termino llamada tranferencia de contexto en donde se agrega un interceptor HTTP el cual en la peticiones HTTP se agrega un header con el usuario que realiza la peticion y este valor se propaga a traves de la aplicacion hasta llegar a la capa de persistencia donde se registra en la base de datos.


Para ejecutar el proyecto se debe tener instalado Java 21 y Maven, ademas de tener configurado un servidor de base de datos compatible con R2DBC. Una vez configurado el entorno, se puede ejecutar la aplicación utilizando el comando `mvn spring-boot:run` desde la terminal en el directorio del proyecto.
Debe levantar un servicio de base de datos compatible con R2DBC, como PostgreSQL, MariaDb, MySQL o H2, y configurar la conexión en el archivo `application.properties` o `application.yml` según corresponda.
La actual configuración esta hecha para conectarse a una base de datos MariaDB, pero se puede cambiar a otra base de datos compatible con R2DBC modificando la configuración correspondiente.
Se debe crear la base de datos antes de ejecutar la aplicación, ya que Flyway se encargará de aplicar las migraciones y crear las tablas necesarias en la base de datos.
La base de datos a crear debe tener el nombre de: my_database

Cuenta con middleware para el manejo de errores, lo que permite capturar y manejar las excepciones de manera centralizada, proporcionando respuestas adecuadas a los clientes y mejorando la experiencia del usuario.
