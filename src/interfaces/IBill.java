package interfaces;

import java.io.Serializable;
import java.math.BigInteger;

public interface IBill extends Serializable {
    String getMovieName();
    BigInteger getOutrageousPrice();
}
