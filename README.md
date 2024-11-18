


# Proyecto Challenge Literalura

Este es un proyecto de consola desarrollado en Java, que permite interactuar con una base de datos y/o con la api de gutendex para gestionar información sobre autores y libros. La aplicación permite realizar consultas sobre autores nacidos en un año específico y mostrar los libros disponibles en función de los idiomas seleccionados.

## Descripción

El proyecto permite a los usuarios gestionar y consultar información de autores y libros. Los principales casos de uso incluyen:

- **Buscar un libro por su titulo**.
- **Listar los libros ya almacenados en un base de datos**.
- **Consultar autores vivos en un determinado año**.
- **Mostrar libros disponibles en uno o más idiomas**.


Este sistema está diseñado para interactuar con una base de datos mediante servicios y consultas que retornan los resultados de manera eficiente. Está construido en Java, utilizando principios de programación orientada a objetos (POO) y patrones de diseño comunes, bajo el Framework Spring

## Tecnologías Utilizadas

- **Java 11 o superior**: Lenguaje de programación utilizado para el desarrollo de la aplicación.
- **Spring Boot (opcional, si el proyecto utiliza este marco)**: Framework para simplificar el desarrollo de aplicaciones Java basadas en Spring.
- **JDBC** o **JPA/Hibernate**: Para la interacción con la base de datos (opcional, según la configuración del proyecto).
- **Base de Datos**: Puede usar cualquier base de datos relacional (por ejemplo, MySQL o PostgreSQL).

## Características

- **Validación de entradas**: Asegura que el usuario ingrese valores válidos, como años en el formato adecuado.
- **Filtrado de libros por idioma**: Consulta eficiente de libros en varios idiomas.
- **Interacción en consola**: Una interfaz amigable para la consola donde el usuario puede realizar varias operaciones.

### Requisitos previos

Antes de comenzar, asegúrate de tener las siguientes herramientas instaladas en tu máquina:

- **Java 11 o superior**.
- **Maven** (si usas Spring Boot o cualquier gestor de dependencias Java).
- **Base de datos** (MySQL, PostgreSQL, etc.) configurada y funcionando.

### Contribuciones

Si deseas contribuir al proyecto, sigue estos pasos:

- 1.- Forkea el repositorio.
- 2.- Crea una nueva rama (git checkout -b feature-nueva-funcionalidad).
- 3.- Realiza tus cambios y haz commit de ellos (git commit -am 'Añadí una nueva funcionalidad').
- 4.- Empuja tus cambios a tu repositorio fork (git push origin feature-nueva-funcionalidad).
- 5.-Crea un Pull Request en este repositorio.


### Licencia

Este proyecto está licenciado bajo la Licencia MIT.

