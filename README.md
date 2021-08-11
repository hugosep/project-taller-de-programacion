# Proyecto Taller de programación

# Dependencias:
Agregar manualmente o con Maven las siguientes dependencias:

* org.apache.activemq:activemq-all:5.16.2
* mysql:mysql-connector-java:8.0.26

# BD

para el uso y creación de la base de datos MUSICA, con la tabla CANCIONES, es la siguiente:

* **File**: create_tables.sql

Y la autorización está configurada con:
* **user**: root
* **password**: 1234+

# data2.txt

El archivo data2.txt contiene solo unas pocas líneas (entre 50 y 100) extraídas desde data.txt, procurando también no incluir inserciones con el carácter ' y así prevenir errores de inserción en tiempo de ejecución.


## Ejecución:

Para ejecutar, se debe seleccionar **Run...** y en el orden **DB, appArch, host (x3)**:


# appArch:
Funciona como productor para la cola, mrRegistro, agregando las canciones y sus características como un string.

# DB:
Correr la clase appDB, la cual consume desde la cola mqRegistro e inserta las nuevas canciones.

# host

La clase host debe ser ejecutada 3 veces en paralelo. En Intelij, esto debe ser configurado previamente: en la barra superior acceder a "Run" - Edit configuration - Seleccionar aplicación de host en el menú de la izquierda - Marcar casilla "allow parallel run" o "allow multiple instances" en el extremo superior de la ventana - Ejecutar host 3 veces