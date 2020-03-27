package fr.polytech.jydet.td5;

import fr.polytech.jydet.td5.initializer.ControllerInitializer;
import fr.polytech.jydet.td5.service.TimeShifterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "TimeShifter",
        urlPatterns = "/libraryTimeShifter"
)
public class TimeShifterController extends HttpServlet {

    private TimeShifterService timeShifterService;

    @Override
    public void init() {
        timeShifterService = ControllerInitializer.getTimeShifterService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String dayPassedStr = req.getParameter("dayPassed");
        if (dayPassedStr != null) {
            try {
                int dayPassed = Integer.parseInt(dayPassedStr);
                timeShifterService.shiftTime(dayPassed);
            } catch (NumberFormatException e) {
                resp.sendRedirect("/libraryHome?TSError=Formulaire%20Invalide");
                return;
            }
        }
        resp.sendRedirect("/libraryHome?TSStatus=Date%20recul%C3%A9e");
    }
}
