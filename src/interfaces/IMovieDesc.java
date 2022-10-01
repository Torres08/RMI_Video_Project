package interfaces;

import java.io.Serializable;

public interface IMovieDesc extends Serializable {
    String getName();

    String getIsbn();

    String getSynopsis();
}
