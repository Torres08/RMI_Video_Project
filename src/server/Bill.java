package server;

import interfaces.IBill;

import java.math.BigInteger;

public class Bill implements IBill {

    private String movieName;
    private  BigInteger price;

    public Bill (String movieName, BigInteger price){
        this.movieName = movieName;
        this.price = price;
    }

    @Override
    public String getMovieName() {
        return null;
    }

    @Override
    public BigInteger getOutrageousPrice() {
        return null;
    }


    @Override
    public String toString(){
        return "Bill: "
                + "\n * Movie Name -> " + movieName
                + "\n * Price -> " + price.toString() +"\n";
    }
}
