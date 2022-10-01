package client;

import interfaces.IClientBox;

import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox {

    public ClientBox() throws RemoteException{}

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
