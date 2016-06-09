package dao;

import entity.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class DbPersist {

    private DataSource dataSource;

    public void persistGenre (Map<String,Genre> genreMap) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM genre");
        String sql = "INSERT INTO genre(id, genre) VALUES (?,?)";
        for(Map.Entry<String, Genre> pair : genreMap.entrySet()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,pair.getValue().getId());
            ps.setString(2,pair.getValue().getGenre());
            ps.executeUpdate();
        }
        System.out.println("persistGenre - DONE");
    }

    public void persistUsers (Map<String,Users> usersMap) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM users");
        String sql = "INSERT INTO USERS(id, name, email, passwd) VALUES (?,?,?,?)";
        for(Map.Entry<String, Users> pair : usersMap.entrySet()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,pair.getValue().getId());
            ps.setString(2,pair.getValue().getName());
            ps.setString(3,pair.getValue().getEmail());
            ps.setString(4,pair.getValue().getPasswd());
            ps.executeUpdate();
        }
        System.out.println("persistUsers - DONE");
    }

    public void persistReview (Map<String,Review> reviewMap) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM review");
        String sql = "INSERT INTO review(id, movieid, userid, review) VALUES (?,?,?,?)";
        for(Map.Entry<String, Review> pair : reviewMap.entrySet()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,pair.getValue().getId());
            ps.setInt(2, pair.getValue().getMovieid());
            ps.setInt(3, pair.getValue().getUserid());
            ps.setString(4,pair.getValue().getReview());
            ps.executeUpdate();
        }
        System.out.println("persistReview - DONE");
    }

    public void persistMovie (Map<String,Movie> movieMap) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM movie");
        String sql = "INSERT INTO movie(id, namerus, nameeng, yr, descr, rating, price) VALUES (?,?,?,?,?,?,?)";
        for(Map.Entry<String, Movie> pair : movieMap.entrySet()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pair.getValue().getId());
            ps.setString(2, pair.getValue().getNamerus());
            ps.setString(3, pair.getValue().getNameeng());
            ps.setInt(4, pair.getValue().getYr());
            ps.setString(5, pair.getValue().getDescr());
            ps.setDouble(6,pair.getValue().getRating());
            ps.setDouble(7,pair.getValue().getPrice());
            ps.executeUpdate();
        }
        System.out.println("persistMovie - DONE");
    }

    public void persistCountry (Map<String,Country> countryMap) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM country");
        String sql = "INSERT INTO country(id, country) VALUES (?,?)";
        for(Map.Entry<String, Country> pair : countryMap.entrySet()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pair.getValue().getId());
            ps.setString(2, pair.getValue().getCountry());
            ps.executeUpdate();
        }
        System.out.println("persistCountry - DONE");
    }

    public void persistMovieCountry (Map<Integer,Movie_Country> movieCountryMap) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM movie_country");
        String sql = "INSERT INTO movie_country(id, movieid, countryid) VALUES (?,?,?)";
        for (Map.Entry<Integer,Movie_Country> pair : movieCountryMap.entrySet()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,pair.getValue().getId());
            ps.setInt(2, pair.getValue().getMovieid());
            ps.setInt(3, pair.getValue().getCountryid());
            ps.executeUpdate();
        }
        System.out.println("persistMovieCountry - DONE");
    }

    public void persistMovieGenre (Map<Integer,Movie_Genre> movieGenreMap) throws IOException, ClassNotFoundException, SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM movie_genre");
        String sql = "INSERT INTO movie_genre(id, movieid, genreid) VALUES (?,?,?)";
        for (Map.Entry<Integer,Movie_Genre> pair : movieGenreMap.entrySet()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pair.getValue().getId());
            ps.setInt(2, pair.getValue().getMovieid());
            ps.setInt(3, pair.getValue().getGenreid());
            ps.executeUpdate();
        }
        System.out.println("persistMovieGenre - DONE");
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}