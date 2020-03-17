package fr.polytech.jydet.td2;

import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(
        name = "HourServlet",
        description = "Un servlet qui donne l'heure",
        urlPatterns = "/hour"
)
public class HourServlet extends HttpServlet {
    private SimpleDateFormat simpleDateFormat;

    public void init() {
        String pattern = "EEEEE dd MMMMM yyyy HH:mm:ss.SSSZ";
        simpleDateFormat = new SimpleDateFormat(pattern, new Locale("fr", "FR"));
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.simpleTextResponse(resp, "Nous sommes le " + simpleDateFormat.format(new Date()));
    }
}
