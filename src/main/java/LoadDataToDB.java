import dao.DbPersist;
import entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;



public class LoadDataToDB {

    public static final String GENRE_FILE_NAME = "src\\main\\resources\\init_txt\\genre.txt";
    public static final String MOVIE_FILE_NAME = "src\\main\\resources\\init_txt\\movie.txt";
    public static final String REVIEW_FILE_NAME = "src\\main\\resources\\init_txt\\review.txt";
    public static final String USER_FILE_NAME = "src\\main\\resources\\init_txt\\user.txt";

    public String[] getArrayFromFile(String fileName, String decimeter) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return sb.toString().split(decimeter);
    }

    public void setGenreMap(String[] genreArray, Map<String,Genre> genreMap) {
        int i = 0;
        for (String line : genreArray) {
            String[] preTbl = line.split("\n");
            Genre tmpGenre  = new Genre();
            tmpGenre.setId(++i);
            tmpGenre.setGenre(preTbl[0]);
            genreMap.put(preTbl[0],tmpGenre);
        }
    }

    public void setUsersMap(String[] usersArray, Map<String, Users> usersMap) {
        int i = 0;
        for (String line : usersArray) {
            String[] preTbl = line.split("\n");
            Users tmpUsers  = new Users();
            tmpUsers.setId(++i);
            tmpUsers.setName(preTbl[0]);
            tmpUsers.setEmail(preTbl[1]);
            tmpUsers.setPassword(preTbl[2]);

            usersMap.put(preTbl[0], tmpUsers);
        }
    }

    public void setReviewMap(String[] reviewArray, Map<String,Review> reviewMap, Map<String,Movie> movieMap,  Map<String,Users> usersMap) {
        int i = 0;
        for (String line : reviewArray) {
            String[] preTbl = line.split("\n");
            Review tmpReview  = new Review();
            tmpReview.setId(++i);
            tmpReview.setMovie(preTbl[0]);
            tmpReview.setUser(preTbl[1]);
            tmpReview.setMovieid(movieMap.get(preTbl[0]).getId());
            tmpReview.setUserid(usersMap.get(preTbl[1]).getId());
            tmpReview.setReview(preTbl[2]);
            reviewMap.put(preTbl[0]+":"+preTbl[1], tmpReview);
        }
    }

    public void setMovieMap(String[] movieArray, Map<String,Movie> movieMap) {
        int i = 0;
        for (String line : movieArray) {
            String[] preTbl = line.split("\n");
            Movie tmpMovie  = new Movie();
            tmpMovie.setId(++i);
            tmpMovie.setNameRus(preTbl[0].split("/")[0]);
            tmpMovie.setNameOrigin(preTbl[0].split("/")[1]);
            tmpMovie.setYear(Integer.parseInt(preTbl[1]));
            tmpMovie.setCountry(preTbl[2]);
            tmpMovie.setGenre(preTbl[3]);
            tmpMovie.setDescription(preTbl[4]);
            tmpMovie.setRating(Double.parseDouble(preTbl[5].substring(7)));
            tmpMovie.setPrice(Double.parseDouble(preTbl[6].substring(6)));
            movieMap.put(preTbl[0].split("/")[0], tmpMovie);
        }
    }

    public void setCountryMap(Map<String,Movie> movieMap, Map<String, Country> countryMap, Map<Integer,Movie_Country> movieCountryMap) {
        int i = 0;
        int j = 0;
        for(Map.Entry<String, Movie> pair : movieMap.entrySet()) {

            for (String bb: pair.getValue().getCountry().split(", ")) {
                if (countryMap.get(bb)==null) {
                    Country country = new Country();
                    country.setId(++j);
                    country.setCountry(bb);
                    countryMap.put(bb, country);
                }
                Movie_Country movie_country = new Movie_Country();
                movie_country.setId(++i);
                movie_country.setMovieid(pair.getValue().getId());
                movie_country.setCountryid(countryMap.get(bb).getId());
                movieCountryMap.put(i,movie_country);
            }
        }
    }

    public void setMovieGenreMap(Map<String,Movie> movieMap, Map<String, Genre> genreMap, Map<Integer,Movie_Genre> movieGenreMap) {
        int i = 0;
        for(Map.Entry<String, Movie> pair : movieMap.entrySet()) {
            for (String bb: pair.getValue().getGenre().split(", ")) {
                Movie_Genre movie_genre = new Movie_Genre();
                movie_genre.setId(++i);
                movie_genre.setMovieid(pair.getValue().getId());
                movie_genre.setGenreid(genreMap.get(bb).getId());
                movieGenreMap.put(i, movie_genre);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Map<String,Genre> genreMap = new HashMap<>();
        Map<String,Users> usersMap = new HashMap<>();
        Map<String,Movie> movieMap =  new HashMap<>();
        Map<String,Review> reviewMap = new HashMap<>();
        Map<String,Country> countryMap = new HashMap<>();
        Map<Integer,Movie_Country> movieCountryMap = new HashMap<>();
        Map<Integer,Movie_Genre> movieGenreMap = new HashMap<>();

        LoadDataToDB loadDataToDB = new LoadDataToDB();
        loadDataToDB.setGenreMap(loadDataToDB.getArrayFromFile(GENRE_FILE_NAME, "\n"), genreMap);
        loadDataToDB.setUsersMap(loadDataToDB.getArrayFromFile(USER_FILE_NAME, "\n\n"), usersMap);
        loadDataToDB.setMovieMap(loadDataToDB.getArrayFromFile(MOVIE_FILE_NAME, "\n\n"), movieMap);
        loadDataToDB.setReviewMap(loadDataToDB.getArrayFromFile(REVIEW_FILE_NAME, "\n\n"), reviewMap, movieMap, usersMap);
        loadDataToDB.setCountryMap(movieMap, countryMap, movieCountryMap);
        loadDataToDB.setMovieGenreMap(movieMap, genreMap, movieGenreMap);

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DbPersist dbPersist = (DbPersist) context.getBean("dbPersist");

        dbPersist.persistGenre(genreMap);
        dbPersist.persistUsers(usersMap);
        dbPersist.persistReview(reviewMap);
        dbPersist.persistMovie(movieMap);
        dbPersist.persistCountry(countryMap);
        dbPersist.persistMovieCountry(movieCountryMap);
        dbPersist.persistMovieGenre(movieGenreMap);
        System.out.println("Done");
    }
}