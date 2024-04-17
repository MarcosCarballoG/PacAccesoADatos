# PacAccesoADatos
pac de desarrollo Acceso a datos(m06)

# Sistema de Gestión de Biblioteca

Este proyecto consiste en un sistema de gestión de biblioteca desarrollado en Java. Utiliza el framework Hibernate para el mapeo objeto-relacional (ORM), permitiendo interactuar con una base de datos MySQL para almacenar y gestionar la información de libros, lectores y préstamos.

## Funcionalidades

El sistema ofrece las siguientes funcionalidades principales:

1. **Gestión de Libros:**
   - **Agregar Libro:** Permite registrar un nuevo libro en la biblioteca, ingresando información como título, autor y año de publicación.
   - **Eliminar Libro:** Permite eliminar un libro de la biblioteca a través de su identificador único (ID).
   - **Actualizar Libro:** Permite modificar la información de un libro existente, incluyendo título, autor y año de publicación.

2. **Gestión de Lectores:**
   - **Agregar Lector:** Permite registrar un nuevo lector en la biblioteca, ingresando información como nombre, apellido, email y fecha de nacimiento.
   - **Eliminar Lector:** Permite eliminar un lector de la biblioteca a través de su identificador único (ID).
   - **Actualizar Lector:** Permite modificar la información de un lector existente, incluyendo nombre, apellido, email y fecha de nacimiento.

3. **Gestión de Préstamos:**
   - **Prestar Libro:** Permite realizar un préstamo de un libro a un lector registrado en la biblioteca. El libro debe estar disponible para ser prestado.
   - **Devolver Libro:** Permite registrar la devolución de un libro prestado, actualizando su estado de disponibilidad.

4. **Consultas:**
   - **Listado de Libros:** Muestra un listado de todos los libros disponibles en la biblioteca.
   - **Listado de Lectores:** Muestra un listado de todos los lectores registrados en la biblioteca.
   - **Detalles de Libro por ID:** Muestra información detallada de un libro específico, identificado por su ID.
   - **Detalles de Lector por ID:** Muestra información detallada de un lector específico, identificado por su ID.
   - **Listado de Libros Prestados a un Lector:** Muestra los libros actualmente prestados a un lector específico.
   - **Listado de Libros Disponibles para Préstamo:** Muestra un listado de todos los libros que están disponibles para ser prestados.
   - **Historial de Préstamos por Lector:** Muestra el historial de préstamos realizados por un lector específico.

## Configuración y Uso

1. **Requisitos Previos:**
   - Tener instalado Java Development Kit (JDK).
   - Tener instalado y configurado MySQL Server.
   - Descargar e instalar Hibernate.

2. **Configuración de la Base de Datos:**
   - Crear una base de datos llamada `m06`.
   - Ejecutar los scripts SQL proporcionados en la carpeta `database-scripts` para crear las tablas necesarias.

3. **Configuración de Hibernate:**
   - Modificar el archivo `hibernate.cfg.xml` para configurar la conexión a la base de datos MySQL.

4. **Compilación y Ejecución:**
   - Compilar el proyecto utilizando el compilador de Java.
   - Ejecutar la clase `Main` para iniciar la aplicación.

5. **Uso del Sistema:**
   - Seleccionar una opción del menú principal ingresando el número correspondiente.
   - Seguir las instrucciones proporcionadas por la interfaz de línea de comandos para interactuar con el sistema.
