package server.business;

import client.presentation.model.Item;
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

}
