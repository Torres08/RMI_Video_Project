package server;
import interfaces.IBill;
import java.io.Serializable;
import java.math.BigInteger;

public class Bill implements IBill {

    private final String movieName;
    private final BigInteger price;

    public Bill (String movieName, BigInteger price){
        this.movieName = movieName;
        this.price = price;
    }

    @Override
    public String getMovieName() {return movieName;}

    @Override
    public BigInteger getOutrageousPrice() {
        return price;
    }

    @Override
    public String toString(){
        return ""
                + "\n * Movie Name -> " + movieName
                + "\n * Price -> " + price.toString() +"\n";
    }
}
