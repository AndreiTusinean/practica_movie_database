package server.data_source;

import client.presentation.model.Movie;
import client.presentation.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import server.business.ReportInterface;
import server.business.ReportPdf;
import server.business.ReportTxt;

import java.io.File;
import java.util.List;

public class AdminActions {

    public static String insertUser(User u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(u);
        session.getTransaction().commit();
        session.close();
        return "addUser," + "Inserted user: " + u.toString();
    }

    public static String updateUser(User u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(u);
        //AdminUI.showMessage("Updated user " + u.getId() + " to " + u.toString());
        session.getTransaction().commit();
        session.close();
        return "updateUser," + "Updated user " + u.getId() + " to " + u.toString();
    }

    public static String deleteUser(User u) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User p where p.id='" + u.getId() + "'");
        List<User> user1 = query.list();
        if (user1.isEmpty())
            str = "removeUser," + "No user found with id " + u.getId();
        else
            str = "removeUser," + "Deleted user " + u.getId();
        for (User j : user1) {
            if (j.getId() == u.getId()) {
                session.delete(j);
                session.getTransaction().commit();
            }
        }
        session.close();
        return str;
    }

    public static String viewUser(User u) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User p where p.id='" + u.getId() + "'");
        List<User> user1 = query.list();
        if (user1.isEmpty()) {
            str = "viewUser," + "No user found with id " + u.getId();
        }
        StringBuilder msg = new StringBuilder("");
        for (User j : user1) {
            if (j.getId() == u.getId()) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "viewUser," + "No user found with id " + u.getId();
        else
            str = "viewUser," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String insertMovie(Movie m) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();
        session.close();
        return "addMovie," + "Inserted movie: " + m.toString();
    }

    public static String updateMovie(Movie m) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(m);
        session.getTransaction().commit();
        session.close();
        return "updateMovie," + "Updated movie " + m.getId() + " to " + m.toString();
    }

    public static String deleteMovie(Movie m) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Movie p where p.id='" + m.getId() + "'");
        List<Movie> movie1 = query.list();
        if (movie1.isEmpty())
            str = "removeMovie," + "No movie found with id " + m.getId();
        else
            str = "removeMovie," + "Deleted movie " + m.toString();
        for (Movie j : movie1) {
            if (j.getId() == m.getId()) {
                session.delete(j);
                session.getTransaction().commit();
            }
        }
        session.close();
        return str;
    }

    public static String viewMovie(Movie m) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Movie p where p.id='" + m.getId() + "'");
        List<Movie> movie1 = query.list();
        if (movie1.isEmpty()) {
            str = "viewMovie," + "No movie found with id " + m.getId();
        }
        StringBuilder msg = new StringBuilder("");
        for (Movie j : movie1) {
            if (j.getId() == m.getId()) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "viewMovie," + "No movie found with id " + m.getId();
        else
            str = "viewMovie," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String makeReportDS(String movie, String remarks, String type) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Movie p where p.name='" + movie + "'");
        List<Movie> movie1 = query.list();
        if (movie1.isEmpty()) {
            str = "report," + "No movies found with name " + movie;
        }
        StringBuilder msg = new StringBuilder("");
        for (Movie j : movie1) {
            if (j.getName().equals(movie)) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "report," + "No movies found with name " + movie;
        else {
            msg.append("\n Remarks: ");
            msg.append(remarks);
            if (type.equals("txt")) {
                File f = ReportInterface.getLocation();
                System.out.println("You selected the directory: " + f.toString());
                System.out.println("..........................TXT......................................");
                ReportInterface ri = new ReportTxt();
                ri.makeReport(msg.toString(), f);
                str = "report," + msg.toString();
            } else {
                File f = ReportInterface.getLocation();
                System.out.println("You selected the directory: " + f.toString());
                System.out.println("..........................PDF......................................");
                ReportInterface ri = new ReportPdf();
                ri.makeReport(msg.toString(), f);
                str = "report," + msg.toString();
            }

        }
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String changeStatus(Movie m) {
        String str = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Movie p where p.name='" + m.getName() + "'");
        List<Movie> movie1 = query.list();
        if (movie1.isEmpty()) {
            str = "changeStatus," + "No movie found with name " + m.getName();
        }
        StringBuilder msg = new StringBuilder("");
        for (Movie j : movie1) {
            if (j.getName().equals(m.getName())) {
                m.setGenre(j.getGenre());
                m.setYear(j.getYear());
                m.setId(j.getId());
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "changeStatus," + "No movie found with name " + m.getName();
        else {
            str = "changeStatus," + "Changed status of movie " + m.getName() + " to " + m.getStatus();
            Movie m1 = new Movie(m.getName(),m.getYear(),m.getGenre(),m.getStatus());
            m1.setId(m.getId());
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(m);
            s.getTransaction().commit();
            s.close();
        }
        session.getTransaction().commit();
        session.close();
        return str;
    }

}
