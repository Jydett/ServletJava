package fr.polytech.jydet.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletUtils {

    public static void simpleTextResponse(HttpServletResponse resp, String text) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head></head><body>" + text + "</body></html>");
    }
}
