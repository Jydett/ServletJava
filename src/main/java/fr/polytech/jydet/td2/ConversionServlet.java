package fr.polytech.jydet.td2;

import fr.polytech.jydet.td2.utils.TemperatureConversion;
import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ConversionServlet",
        description = "Un servlet pour traduire les température",
        urlPatterns = "/conversion"
)
public class ConversionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.simpleTextResponse(resp, getForm(null));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String celsiusValue = req.getParameter("celsiusValue");
        if (celsiusValue == null) {
            doGet(req, resp);
        } else {
            try {
                final double value = Double.parseDouble(celsiusValue);
                ServletUtils.simpleTextResponse(resp, getForm(Double.toString(TemperatureConversion.celsiusToFahrenheit(value))));
            } catch (NumberFormatException e) {
                ServletUtils.simpleTextResponse(resp, "La valeur saisie n'est pas un nombre !");
            }
        }
    }

    private String getForm(String response) {
        return "<html>\n" +
                "<head></head>\n" +
                "<body style='background-color: #ccff66'>\n" +
                "<form action=\"\" method=\"post\">\n" +
                "    <div>\n" +
                "        <div><input type=\"number\" name=\"celsiusValue\" id=\"celsiusValue\" value='0'><label for='celcius'>Température en °C</label></div>\n" +
                "    </div>\n" +
                "    <div class=\"form-example\">\n" +
                "        <input type=\"submit\" value=\"Convertir en °F\">\n" +
                "    </div>\n" +
                "    <div style='border: #ff1118 1px solid'>\n" +
                "        " + (response == null ? "0" : response) + "°F\n" +
                "</div>\n" +
                "</form>" +
                "</body>\n" +
                "</html>";
    }
}
