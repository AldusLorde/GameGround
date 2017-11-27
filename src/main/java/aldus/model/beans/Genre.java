package aldus.model.beans;

public class Genre {
    private int id = 0;
    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }

    public Genre(int id, String genre) {
        this(genre);
        this.id = id;
    }


    public Genre() {
        id = 0;
        genre = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        genre = genre;
    }
}
