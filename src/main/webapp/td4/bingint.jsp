<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BigIntConverter</title>
</head>
<body>
    <%
        String number = request.getParameter("number");
        if (number != null) {
            try {
                BigInteger bigInteger = new BigInteger(number);
                out.println(number + "^2 =" + bigInteger.pow(2));
            } catch (NumberFormatException e) {
                out.println("NaN");
            }
        } else {
            out.println("Pas de numéro (number=?)");
        }
    %>
</body>
</html>
