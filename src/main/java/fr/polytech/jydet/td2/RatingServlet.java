package fr.polytech.jydet.td2;

import fr.polytech.jydet.td2.storage.BasicConnectionPool;
import fr.polytech.jydet.utils.FormBuilder;
import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(
        name = "RatingServlet",
        description = "A servlet to rate students",
        urlPatterns = "/rate",
        initParams = {
                @WebInitParam(name = "port", value = "3308"),
                @WebInitParam(name = "host", value = "localhost"),
                @WebInitParam(name = "base", value = "servlet"),
                @WebInitParam(name = "user", value = "root"),
                @WebInitParam(name = "pwd", value = ""),
        }
)
public class RatingServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        String port = config.getInitParameter("port");
        String host = config.getInitParameter("host");
        String base = config.getInitParameter("base");
        String user = config.getInitParameter("user");
        String pwd = config.getInitParameter("pwd");
        try {
            BasicConnectionPool.create(Integer.parseInt(port), host, base, user, pwd);
            Connection connection = BasicConnectionPool.INSTANCE.getConnection();
            ResultSet tables = connection.getMetaData().getTables(null, null, "student_rating", new String[]{"TABLE"});
            if (! tables.next()) {
                String createTable = "CREATE TABLE `servlet`.`student_rating` ( `id` INT NOT NULL AUTO_INCREMENT , `student_id` INT NOT NULL , `rating` DOUBLE NOT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM; ";
                connection.createStatement().execute(createTable);
            }
            BasicConnectionPool.INSTANCE.releaseConnection(connection);
            super.init(config);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.simpleTextResponse(resp,
                new FormBuilder(FormBuilder.Method.POST)
                    .addField(FormBuilder.FieldType.TEXT, "fullname", "Nom de l'étudiant", "")
                    .addField(FormBuilder.FieldType.TEXT, "name", "Prénom de l'étudiant", "")
                    .addField(FormBuilder.FieldType.NUMBER, "number", "Numéro de l'étudiant", "")
                    .addField(FormBuilder.FieldType.NUMBER, "rating", "Note de l'étudiant", "0")
                    .addSubmit("Enregister la note")
                    .build()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullname = req.getParameter("fullname");
        String name = req.getParameter("name");
        String number = req.getParameter("number");
        String rating = req.getParameter("rating");

        if (
                fullname == null || fullname.length() == 0 ||
                name == null || name.length() == 0 ||
                number == null || number.length() == 0 ||
                rating == null || rating.length() == 0
        ) {
            ServletUtils.simpleTextResponse(resp, "Formulaire invalide: champs manquant");
            return;
        }
        int studentNumber;
        double studentRating;
        try {
            studentNumber = Integer.parseInt(number);
            studentRating = Double.parseDouble(rating);
            if (studentRating < 0 || studentRating > 20) {
                ServletUtils.simpleTextResponse(resp, "Formulaire invalide: Note invalide");
                return;
            }
        } catch (NumberFormatException e) {
            ServletUtils.simpleTextResponse(resp, "Formulaire invalide: Note invalide");
            return;
        }
        try {
            double moy = insertAndGetMean(fullname, name, studentNumber, studentRating);
            ServletUtils.simpleTextResponse(resp, "Note enregistré, moy à cet instant " + moy);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Une erreur est survenue !");
        }
    }

    private double insertAndGetMean(String fullname, String name, int studentNumber, double studentRating) throws SQLException {
        //Les attributs fullname et name ne sont pas utilisé, pour faire bien il ne faudrais pas els envoyer dans le formulaire
        // et les stockés dans une table à part student(id, fullname, name), mais bon je les envoies pour rester proche de l'exercice
        // (même si je ne m'en sert jamais)
        Connection connection = BasicConnectionPool.INSTANCE.getConnection();
        connection.createStatement().execute("INSERT INTO student_rating(student_id, rating) VALUES (" + studentNumber + "," + studentRating + ")");
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT AVG(rating) as avg FROM student_rating");
        if (resultSet.next()) {
            return resultSet.getDouble("avg");
        }
        BasicConnectionPool.INSTANCE.releaseConnection(connection);
        return 0;
    }

    @Override
    public void destroy() {
        try {
            BasicConnectionPool.INSTANCE.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.destroy();
    }
}
