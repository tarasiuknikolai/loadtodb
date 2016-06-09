package entity;

public class Review {
    private int id;
    private int movieid;
    private int userid;
    private String movie; //
    private String user;  //
    private String review;

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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", movieid=" + movieid +
                ", userid=" + userid +
                ", movie='" + movie + '\'' +
                ", user='" + user + '\'' +
                ", review='" + review.substring(0,21) + '\'' +
                '}';
    }
}
