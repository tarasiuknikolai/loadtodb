package entity;

public class Movie_Country {
    private int id;
    private int movieid;
    private int countryid;

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

    public int getCountryid() {
        return countryid;
    }

    public void setCountryid(int countryid) {
        this.countryid = countryid;
    }

    @Override
    public String toString() {
        return "Movie_Country{" +
                "id=" + id +
                ", movieid=" + movieid +
                ", countryid=" + countryid +
                '}';
    }
}
