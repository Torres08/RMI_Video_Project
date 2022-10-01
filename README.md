# RMI Video Proyect
The goal is to program in RMI a client-server application that simulates an online platform that delivers Videos On Demand (video=film).



## Estructura

Este trabajo se ha dividido en 4 paquetes: 
### client
habla de las funciones y eso
### server
### interfaces
### exceptions


## Usage
### El flujo del trabajo:

1. El cliente se registra (si es necesario)
2. El cliente se conecta para recuperar el servicio VOD
3. El cliente llama a viewCatalog() para recuperar las descripciones de las peliculas, suponenos que el servidor da un catalogo de peliculas predefinidas y por cada pelicula una descripcion de la clase MovieDesc
4. Muestra la lista de peliculas en la consola (sinopsis, y si es un MovieDescExtend, reproduce automaticamente el trailer y ofrece al usuario elejir una peli)
5. Cuando se selecciona una peli en la consola del cliente -> llama a playMovie()
   1. El servidor envia el primer bloque , llamando al metodo stream() del cliente proporcionandole el comienzo de la pelicula (se mostrara en la salida estandar del cliente)
   2. El servidor inicia un hilo localmente para seguir trasmitiendo los otro bloques uno tras otro -> stream()
   3. El servidor devuelve la factura como retorno del metodo playMovie

## Credits

TODO: Write credits


