package server;

import interfaces.IMovieDesc;
import java.math.BigInteger;
import java.util.Objects;

public class Movie implements IMovieDesc {

    private String name;
    private String isbn;
    private String synopsis;
    private String[] videoStream;
    private BigInteger price;

    public Movie(String name, String isbn, String synopsis, String[] videoStream, BigInteger price){
        this.name = name;
        this.isbn = isbn;
        this.price = price;
        this.videoStream = videoStream;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String[] getVideoStream() {
        return videoStream;
    }

    public BigInteger getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(isbn, movie.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    public String toString(){
        return "\nName: " + name + "\nISBN: " + isbn + "\nPrice: " + price + "\nSynopsis: " + synopsis + "\n";
    }

}
