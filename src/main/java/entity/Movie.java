package entity;

public class Movie {
    private int id;
    private String namerus;
    private String nameeng;
    private int yr;
    private String country; //
    private String genre; //
    private String descr;
    private double rating;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamerus() {
        return namerus;
    }

    public void setNamerus(String namerus) {
        this.namerus = namerus;
    }

    public String getNameeng() {
        return nameeng;
    }

    public void setNameeng(String nameeng) {
        this.nameeng = nameeng;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", namerus='" + namerus + '\'' +
                ", yr=" + yr +
                '}';
    }
}
