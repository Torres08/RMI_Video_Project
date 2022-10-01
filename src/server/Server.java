package server;

import interfaces.IConnection;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) throws RemoteException {
       // System.out.println("Hola hola");

        Registry registry = LocateRegistry.createRegistry(2002);

        String resourcePath = Paths.get("").toAbsolutePath().toString() + "/src/server/resources";
        // /home/icaro/Escritorio/RMO-video/RMO-video/src/server/resources
        //System.out.println(resourcePath);

        IConnection connection = createConnection(resourcePath + "/clients.txt", resourcePath + "/movies.txt" );

        UnicastRemoteObject.exportObject(connection,10098);

        registry.rebind("Connection", connection);

        System.out.println("Server is ready");
    }

    private static IConnection createConnection(String clientFilePath,  String movieFilePath )throws RemoteException {

        try{
            return new Connection(clientFilePath, movieFilePath);
        }catch (FileNotFoundException e){
            System.out.println("Client file can't be found. Starting Server without loaded clients.");
            try {
                return new Connection(movieFilePath);
            } catch (FileNotFoundException ex) {
                System.out.println("Movie file cant be found. Terminating! ");
            }
        }
        System.exit(1000);
        return null;

    }
}
