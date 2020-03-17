package fr.polytech.jydet.td2;

import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

@WebServlet(
        name = "ImageServlet",
        description = "A servlet to upload an image",
        urlPatterns = "/image"
)
@MultipartConfig
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.simpleTextResponse(resp, "<form action=\"\" method=\"post\" enctype='multipart/form-data'>\n" +
                "    <div>Choisir une image:\n" +
                "    <input type=\"file\" name=\"file\" id=\"file\" accept=\"image/*\"></div>\n" +
                "    <div><input type=\"submit\" value=\"Uploader l'image\" name=\"submit\"></div>\n" +
                "</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        if (fileName == null) {
            ServletUtils.simpleTextResponse(resp, "Invalid form !");
            return;
        }

        OutputStream os = resp.getOutputStream();
        try(InputStream is = filePart.getInputStream()) {
            if (is == null) {
                ServletUtils.simpleTextResponse(resp, "Failed to upload img");
                return;
            }
            int bytesRead;
            byte[] buffer = new byte[1024];
            resp.setContentType("image/png");

            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
