package interfaces;

import exceptions.NoSuchMovieException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVODService extends Remote {

    List<IMovieDesc> viewCatalog() throws RemoteException;

    IBill playMovies(String isbn, IClientBox clientBox) throws NoSuchMovieException, RemoteException;

}
