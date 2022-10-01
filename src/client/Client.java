package client;

import interfaces.IClientBox;
import interfaces.IConnection;
import interfaces.IVODService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws RemoteException {

        //System.out.println("Hola");

        Registry registry = LocateRegistry.getRegistry("localhost",2002 );
        IConnection connection;
        IClientBox clientBox = new ClientBox();

        try{
            connection = (IConnection) registry.lookup("Connection");
        }catch(NotBoundException e){
            System.out.println("Server not avaliable. Terminating ヽ( `д´*)ノ");
            System.exit(1000);
            return;
        }

        ConsoleInterface ui = new ConsoleInterface();
        IVODService vodService = ui.start(connection);
        while(true){
            ui.startMovie(vodService,clientBox);
        }

    }
}
