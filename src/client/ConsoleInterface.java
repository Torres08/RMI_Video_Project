package client;

import exceptions.InvalidCredentialException;
import exceptions.NoSuchMovieException;
import interfaces.IBill;
import interfaces.IClientBox;
import interfaces.IConnection;
import interfaces.IVODService;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ConsoleInterface {

    Scanner in = new Scanner(System.in);

    public IVODService start(IConnection connection){
        System.out.println("Welcome to the VOD Client (＠＾◡＾)");
        System.out.print("Please login (l) or register (r):");
        while (true) {
            String input = in.nextLine();

            if (input.equals("l"))
                return login(connection);
            if (input.equals("r"))
                return signUp(connection);

            System.out.println("Invalid command.");
            System.out.print("Please login (l) or register (r):");
        }
    }


    public IVODService login(IConnection connection){
        System.out.println("Please enter your credentials (login)");

        while(true){
            System.out.println("Email: ");
            String email = in.nextLine();

            System.out.println("Password:");
            String password = in.nextLine();

            try{
                return connection.login(email,password);
            }catch(InvalidCredentialException e){
                System.out.println(e.getMessage());
                System.out.println("Please reenter your credentials.");

            }catch (RemoteException e){
                e.printStackTrace();
                System.exit(1001);
            }
        }
    }

    public IVODService signUp(IConnection connection){
        System.out.println("Please enter your credentials (signUp)");

        while(true){
            System.out.println("Email: ");
            String email = in.nextLine();

            System.out.println("Password:");
            String password = in.nextLine();

            try{
                connection.singUp(email,password);
                return connection.login(email,password);
            }catch(InvalidCredentialException e){
                System.out.println(e.getMessage());
                System.out.println("Please reenter your credentials.");

            }catch (RemoteException e){
                e.printStackTrace();
                System.exit(1001);
            }
        }
    }


    public IBill startMovie(IVODService service, IClientBox clientBox) throws RemoteException {
        System.out.println("The following movies are avaliable: ");

        try{
            service.viewCatalog().stream().forEach(System.out::println);
        } catch (RemoteException e){
            e.printStackTrace();
            System.exit(1001);
        }

        while (true){
            System.out.println("Please enter the ISBN of the movie you want to watch: ");
            String isbn = in.nextLine();

            try{
                IBill bill = service.playMovies(isbn,clientBox);
                System.out.println("Bill to be paid: " + bill.toString());
                return bill;
            }catch(NoSuchMovieException e){
               System.out.println(e.getMessage());
            }catch(RemoteException e){
                e.printStackTrace();
                System.exit(1001);
            }
        }
    }
}
