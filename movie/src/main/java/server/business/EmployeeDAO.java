package server.business;

import client.presentation.model.Favorite;
import client.presentation.model.Movie;
import server.data_source.EmployeeActions;


public class EmployeeDAO {

    public static String searchMovieNameDAO(Movie m) {
        return EmployeeActions.searchMovieName(m);
    }

    public static String searchMovieYearDAO(Movie m) {
        return EmployeeActions.searchMovieYear(m);
    }

    public static String searchMovieGenreDAO(Movie m) {
        return EmployeeActions.searchMovieGenre(m);
    }

    public static String searchMovieStatusDAO(Movie m) {
        return EmployeeActions.searchMovieStatus(m);
    }

    public static String addFavoriteDAO(Favorite f) { return EmployeeActions.addFavorite(f); }

    public static String sendMailDAO(String s, String c) { return EmployeeActions.sendMail(s,c);}

}
