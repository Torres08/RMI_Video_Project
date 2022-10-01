# RMI Video Proyect
The goal is to program in RMI a client-server application that simulates an online platform that delivers Videos On Demand (video=film).

## API

   ### Server Side
   * Connection
      - Se encarga del Login y del SignUp, ademas de guardar los datos y cargarlos desde los ficheros correspondientes
   * VODService
      - Se encarga de enseñar el catalogo y de reproducir la pelicula.
   * Bill
      - Tiene informacion acerca del nombre de la pelicula y del precio
   * Movie
      - Tiene informacion sobre la pelicula, ademas de su respectivo VideoStream
   * Account
      - Tiene informacion sobre email y contraseña del usuario
   * Server
      - Main del servidor


   ### ClientSide
   * Client
      - main del cliente
   * ClientBox
     -  Se encarga de ir streameando la pelicula
   * ConsoleInterface
      - Es la interfaz del cliente RMI.
   

## The Flow

1. Customer registers (if necessary)
2. Customer connects to retrieve the VOD service.
3. The client calls viewCatalog() to retrieve the movie descriptions, we assume that the server gives a predefined movie catalog and for each movie a description of the MovieDesc class.
4. Show the list of movies in the console (synopsis, and if it is a MovieDescExtend, it automatically plays the trailer and offers the user to choose a movie).
5. When a movie is selected in the client console -> call playMovie()
   1. The server sends the first block, calling the stream() method of the client providing the start of the movie (it will be shown in the standard client output).
   2. The server starts a thread locally to keep streaming the other blocks one after the other -> stream()
   3. The server returns the invoice as a return of the playMovie method.


## Instalation
When you have installed the .zip, first start the server in the server folder. Depending on whether you are using Linux or Windows the pathfile where the resource is located changes (linux -> / ; Windows -> -> ).
Start the server first, then the client.




