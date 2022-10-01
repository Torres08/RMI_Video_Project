package server;

import exceptions.NoSuchMovieException;
import interfaces.IBill;
import interfaces.IClientBox;
import interfaces.IMovieDesc;
import interfaces.IVODService;

import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class VODService extends UnicastRemoteObject implements IVODService  {


    private Set<Movie> movies;

    public VODService(Set<Movie> movies) throws RemoteException{
        super();
        this.movies = movies;
    }

    @Override
    public List<IMovieDesc> viewCatalog() {
        return new ArrayList<>(movies);
    }

    /**
     * Plays a Movie depends of his ISBN
     * @param isbn
     * @param clientBox
     * @return streams the movie and return the Bill
     * @throws NoSuchMovieException
     */
    @Override
    public IBill playMovies(String isbn, IClientBox clientBox) throws NoSuchMovieException {
        Movie movie = movies.stream()
                .filter(m -> m.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new NoSuchMovieException("There is not movie registered with the ISBN " + isbn));

        Arrays.stream(movie.getVideoStream()).forEach(str -> {
            try{
                clientBox.stream(str.getBytes(StandardCharsets.UTF_8));
            }catch(RemoteException e){
                e.printStackTrace();;
            }

        });

        return new Bill(movie.getName(), movie.getPrice());

    }

}
