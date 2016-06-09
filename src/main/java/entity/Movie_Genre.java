package entity;

public class Movie_Genre {
    private int id;
    private int movieid;
    private int genreid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    @Override
    public String toString() {
        return "Movie_Genre{" +
                "id=" + id +
                ", movieid=" + movieid +
                ", genreid=" + genreid +
                '}';
    }
}
