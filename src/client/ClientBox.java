package client;
import interfaces.IClientBox;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox {

    public ClientBox() throws RemoteException{}

    /**
     * Show the movie by blocks within 1000 sleep
     * @param chunk (fragments of the movie)
     * @throws RemoteException
     */
    @Override
    public void stream(byte[] chunk) throws RemoteException {

        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(new String(chunk, StandardCharsets.UTF_8));
    }


}
