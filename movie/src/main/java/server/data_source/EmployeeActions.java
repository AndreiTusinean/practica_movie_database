package server.data_source;

import client.presentation.model.Favorite;
import client.presentation.model.Movie;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmployeeActions {


    public static String searchMovieName(Movie m) {
        String str;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Movie p where p.name='" + m.getName() + "'");
        List<Movie> movie1 = query.list();
        if (movie1.isEmpty()) {
            str = "searchName," + "No movie found with name " + m.getName();
        }
        StringBuilder msg = new StringBuilder("");
        for (Movie j : movie1) {
            if (j.getName().equals(m.getName())) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "searchName," + "No movie found with name " + m.getName();
        else
            str = "searchName," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }


    public static String searchMovieYear(Movie m) {
        String str;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Movie p where p.year='" + m.getYear() + "'");
        List<Movie> movie1 = query.list();
        if (movie1.isEmpty()) {
            str = "searchYear," + "No movie found with year " + m.getYear();
        }
        StringBuilder msg = new StringBuilder("");
        for (Movie j : movie1) {
            if (j.getYear() == m.getYear()) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "searchYear," + "No movie found with year " + m.getYear();
        else
            str = "searchYear," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String searchMovieGenre(Movie m) {
        String str;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Movie p where p.genre='" + m.getGenre() + "'");
        List<Movie> movie1 = query.list();
        if (movie1.isEmpty()) {
            str = "searchGenre," + "No movie found with genre " + m.getGenre();
        }
        StringBuilder msg = new StringBuilder("");
        for (Movie j : movie1) {
            if (j.getGenre().equals(m.getGenre())) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "searchGenre," + "No movie found with genre " + m.getGenre();
        else
            str = "searchGenre," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String searchMovieStatus(Movie m) {
        String str;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Movie p where p.status='" + m.getStatus() + "'");
        List<Movie> movie1 = query.list();
        if (movie1.isEmpty()) {
            str = "searchStatus," + "No movie found with status " + m.getStatus();
        }
        StringBuilder msg = new StringBuilder("");
        for (Movie j : movie1) {
            if (j.getStatus().equals(m.getStatus())) {
                msg.append(j.toString()).append("\n");
            }
        }
        if (msg.length() == 0)
            str = "searchStatus," + "No movie found with status " + m.getStatus();
        else
            str = "searchStatus," + msg.toString();
        session.getTransaction().commit();
        session.close();
        return str;
    }

    public static String addFavorite(Favorite f) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(f);
        session.getTransaction().commit();
        session.close();
        return "addFavorite," + "Inserted favorite: " + f.toString();
    }

    public static String sendMail(String subject, String content) {
        String str = "";
        String to = "andrei.tusinean@gmail.com";
        String from = "andrei.tusinean@gmail.com";
        String host = "smtp.gmail.com";//""localhost";
        String mail = "andrei.tusinean@gmail.com";
        String pass = "paddingtonmarmalade,./";

        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.user", mail);
        properties.put("mail.smtp.password", pass);

        javax.mail.Session session = javax.mail.Session.getInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message, mail, pass);
            str = "sendMail,Mail was sent to admin";
        } catch (MessagingException mex) {
            //mex.printStackTrace();
            str = "sendMail,Mail was not sent";
        }
        return str;
    }


}
