package server;

import exceptions.InvalidCredentialException;
import exceptions.SignInFailed;
import interfaces.IConnection;
import interfaces.IVODService;
import java.io.*;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.*;


public class Connection implements IConnection {

    private Set<Account> clients;
    private Set<Movie>  movies;
    private String clientFilePath;
    private VODService vodService;

    /**
     * Initializes a new instance of the Connection class with an empty list of clients.
     *
     * @param movieFilePath path to the file where the movies are persisted.
     * @throws FileNotFoundException is thrown if the movies file does not exist
     * @throws RemoteException is used for RMI
     */
    public Connection(String movieFilePath) throws FileNotFoundException, RemoteException {
        clients = new HashSet<>();
        loadMovieFromFile(movieFilePath);
        vodService = new VODService(movies);
    }

    /**
     * Initializes a new instance of the Connection class.
     *
     * @param clientFilePath path to the file where the clients are persisted.
     * @param movieFilePath path to the file where the movies are persisted.
     * @throws FileNotFoundException is thrown if the movies or client file does not exist
     * @throws RemoteException is used for RMI
     */
    public Connection(String clientFilePath, String movieFilePath) throws FileNotFoundException, RemoteException {
        this.clientFilePath = clientFilePath;
        loadClientsFromFile(clientFilePath);
        loadMovieFromFile(movieFilePath);
        vodService = new VODService(movies);
    }

    /**
     * Load the clients from a file by his filePath
     * @param filePath where is the file
     * @throws FileNotFoundException
     */

    private void loadClientsFromFile(String filePath) throws FileNotFoundException{
        clients = new HashSet<>();

        File file = new File(filePath);
        Scanner scanner = new Scanner (file);

        while(scanner.hasNextLine()){
            String[] parts = scanner.nextLine().split(";");
            clients.add(new Account(parts[0], parts[1]));
        }


    }

    /**
     * Load the movies from a file by his filePath
     * @param filePath where is the file
     * @throws FileNotFoundException
     */
    private void loadMovieFromFile(String filePath) throws FileNotFoundException{
        movies = new HashSet<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String[] parts = scanner.nextLine().split(";");
            movies.add(new Movie (parts[0], parts[1], parts[2], splitStream(parts[3]), new BigInteger(parts[4])));
        }
    }

    /**
     * Split the full Stream of a movie into streameable chunks
     * @param streamInput the full stream
     * @return list of chunks
     */
    private String[] splitStream (String streamInput){
        List<String> stream = new LinkedList<>();
        int chunkSize = 4; // size of the chunks -> 4
        int index = 0;

        while (index < streamInput.length()){
            stream.add(streamInput.substring(index, Math.min(index + chunkSize,streamInput.length()-1)));
            index += 4;
        }

        String [] result = new String[stream.size()];
        return stream.toArray(result);
    }

    /**
     * Appends an account to the clients file.
     * Saving the account for the next start.
     *
     * @param client account to be persisted
     * @throws IOException is thrown if the file can't be written.
     */
    private void saveClientToFile(Account client) throws IOException{
        FileWriter fw = new FileWriter(clientFilePath, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(client.toString());
        bw.close();
    }


    /**
     * SignUp Method
     * @param mail used as an identifier
     * @param password password for the user
     * @return true If it has done without exceptions
     * @throws RemoteException
     * @throws SignInFailed
     */
    @Override
    public boolean singUp(String mail, String password) throws RemoteException, SignInFailed {
        Account newAccount = new Account(mail, password);
        if(!clients.add(newAccount))
            throw  new SignInFailed("The mail " + mail + " is already in use" + "\n");

        try {
            saveClientToFile(newAccount);
        } catch (IOException e){
            clients.remove(newAccount);
            throw new SignInFailed("The mail " + mail + " is already in use " + "\n");
        }

        return true;
    }

    /**
     * Login Method
     * @param mail used as an identifier
     * @param password password for the user
     * @throws RemoteException
     * @throws InvalidCredentialException
     */
    @Override
    public IVODService login(String mail, String password) throws RemoteException, InvalidCredentialException {
        if (clients.stream().anyMatch(a-> a.matchCredential(mail, password) ))
            return vodService;

        throw new InvalidCredentialException("The mail and password dont match.");

    }
}
