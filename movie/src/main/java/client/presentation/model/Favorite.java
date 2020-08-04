package client.presentation.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    @Column(name="ID")
    private int id;
    @Column(name = "movie_name")
    private String movieName;
    @Column(name = "user_name")
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String toString() {
        return id + " " + movieName + " is a favorite movie for " + userName;
    }

    public Favorite(String movieName,String userName){
        this.movieName=movieName;
        this.userName=userName;
    }

    public Favorite(){}
}
