package server;

import exceptions.InvalidCredentialException;
import exceptions.SignInFailed;
import interfaces.IConnection;
import interfaces.IVODService;

import java.io.*;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.*;

/**
 * @title Connection Class
 * Se trata de una clase UnicastRemoteObject que implementa metodos definidos
 * de la interfaz IConnection
 */

public class Connection implements IConnection {

    private Set<Account> clients;
    private Set<Movie>  movies;

    private String clientFilePath;

    private VODService vodService;

    public Connection(String movieFilePath) throws FileNotFoundException, RemoteException {
        clients = new HashSet<>();
        loadMovieFromFile(movieFilePath);
        loadClientsFromFile(clientFilePath);

        vodService = new VODService(movies);
    }

    public Connection(String clientFilePath, String movieFilePath) throws FileNotFoundException, RemoteException {
        this.clientFilePath = clientFilePath;
        loadClientsFromFile(clientFilePath);
        loadMovieFromFile(movieFilePath);
        vodService = new VODService(movies);
    }


    private void loadClientsFromFile(String filePath) throws FileNotFoundException{
        clients = new HashSet<>();

        File file = new File(filePath);
        Scanner scanner = new Scanner (file);

        while(scanner.hasNextLine()){
            String[] parts = scanner.nextLine().split(";");
            clients.add(new Account(parts[0], parts[1]));
        }


    }

    private void loadMovieFromFile(String filePath) throws FileNotFoundException{
        movies = new HashSet<>();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String[] parts = scanner.nextLine().split(";");
            movies.add(new Movie (parts[0], parts[1], parts[2], splitStream(parts[3]), new BigInteger(parts[4])));
        }
    }

    private String[] splitStream (String streamInput){
        List<String> stream = new LinkedList<>();
        int chunkSize = 4;
        int index = 0;

        while (index < streamInput.length()){
            stream.add(streamInput.substring(index, Math.min(index + chunkSize,streamInput.length()-1)));
            index += 4;
        }

        String [] result = new String[stream.size()];
        return stream.toArray(result);
    }

    private void saveClientToFile(Account client) throws IOException{
        FileWriter fw = new FileWriter(clientFilePath, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(client.toString());
        bw.close();
    }



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

    @Override
    public IVODService login(String mail, String password) throws RemoteException, InvalidCredentialException {
        if (clients.stream().anyMatch(a-> a.matchCredential(mail, password) ))
            return vodService;

        throw new InvalidCredentialException("The mail and password dont match.");

    }
}
