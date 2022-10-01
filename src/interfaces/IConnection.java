package interfaces;

import exceptions.InvalidCredentialException;
import exceptions.SignInFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @title IConnection
 * Contains all the functionality that is needed to start a connection to the server.
 */

public interface IConnection extends Remote {

    /**
     * Registers a new user with the given credentials.
     *
     * @param mail used as an identifier
     * @param password password for the user
     * @return whether the registration was successful
     * @throws RemoteException used for RMI
     * @throws SignInFailed thrown when the mail does not have the mail format or already used
     */

    boolean singUp (String mail, String password) throws RemoteException, SignInFailed;

    /**
     * Login of an already registered user.
     *
     * @param mail used as an identifier
     * @param password password for the user
     * @return the vod service used to start movies
     * @throws RemoteException used for RMI
     * @throws InvalidCredentialException thrown when no user with the given credentials is registered.
     */

    IVODService login (String mail, String password) throws RemoteException, InvalidCredentialException;
}
