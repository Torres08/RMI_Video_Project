package server;

import java.util.Objects;

/**
 * Contains all the information of an account and all the functionality to interact with it.
 */

public class Account {

    private String mail;
    private final String password;

    public Account(String mail, String password){
        this.mail = mail;
        this.password = password;
    }

    public boolean matchCredential (String mail, String password){
        return this.mail.equals(mail) && this.password.equals(password);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(mail, account.mail);
    }

    @Override
    public int hashCode(){return Objects.hash(mail);}

    public String toString(){return mail + ';' + password;}

}
