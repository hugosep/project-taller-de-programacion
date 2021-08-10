# Proyecto Taller de programación

# 
# BD

* **File**: database.sql
* **user**: root
* **password**: 1234+

Se genera una tabla _MUSICA_, la cual tiene los atributos _name, genre, autor_.

# data.txt

El formato apra cada ¿línea de este archvo es:
nombre, genero, autor.

Se procesa por línnea, donde se creará un diccionario para cada elemento, se insertará en la BD y se les enviará a los hosts.

# data2.txt

El archivo data.txt tiene algunos títulos de canciones con el carácter ', lo que causa un error al intentar insertar dichas líneas en la bd. Además, ese archivo tarda mucho tiempo en cargarse en hacia la base de datos. 
El archivo data2.txt contiene solo unas pocas líneas (entre 50 y 100) extraídas desde data.txt, procurando también no incluir inserciones con el carácter mencionado anteriormente y así prevenir errores de inserción en tiempo de ejecución.

#host

La clase host debe ser ejecutada 3 veces en paralelo. En Intelij, esto debe ser configurado previamente: en la barra superior acceder a "Run" - Edit configuration - Seleccionar aplicación de host en el menú de la izquierda - Marcar casilla "allow parallel run" en el extremo superior de la ventana - Ejecutar host 3 veces