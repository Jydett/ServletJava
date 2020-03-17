package fr.polytech.jydet.td2;

import fr.polytech.jydet.td2.utils.FormBuilder;
import fr.polytech.jydet.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "CalculatorServlet",
        description = "Une simple calculette",
        urlPatterns = "/calculator"
)
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.simpleTextResponse(resp,
                new FormBuilder(FormBuilder.Method.POST)
                    .addField(FormBuilder.FieldType.NUMBER, "operand1", "Première opérande", "0")
                    .addRadio("operator")
                        .addRadioButton("+", true)
                        .addRadioButton("-", true)
                        .addRadioButton("/", true)
                        .addRadioButton("x", true)
                        .build()
                    .addField(FormBuilder.FieldType.NUMBER, "operand2", "Deuxième opérande", "0")
                    .addSubmit("Calculer").build()
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operand1 = req.getParameter("operand1");
        String operator = req.getParameter("operator");
        String operand2 = req.getParameter("operand2");
        if (operand1 == null || operator == null || operand2 == null) {
            ServletUtils.simpleTextResponse(resp, "Invalid form !");
        } else {
            try {
                Double op1 = Double.parseDouble(operand1);
                Double op2 = Double.parseDouble(operand2);
                double res;
                switch (operator) {
                    case "+": res = op1 + op2; break;
                    case "x": res = op1 * op2; break;
                    case "/": res = op1 / op2; break;
                    case "-": res = op1 - op2; break;
                    default: ServletUtils.simpleTextResponse(resp, "Invalid operator !"); return;
                }
                ServletUtils.simpleTextResponse(resp, operand1 + " " + operator + " " + operand2 + " = " + res);
            } catch (NumberFormatException e) {
                ServletUtils.simpleTextResponse(resp, "Invalid operands -> nan");
            }
        }
    }
}
