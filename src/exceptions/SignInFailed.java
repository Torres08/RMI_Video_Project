package exceptions;

public class SignInFailed extends RuntimeException {
    public SignInFailed(String message){
        super(message);
    }
}
