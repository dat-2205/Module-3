<%--
  Created by IntelliJ IDEA.
  User: dat01
  Date: 9/28/2021
  Time: 1:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <%!
        String fist_operand;
        String operator;
        String second_operand;
        Float result;
    %>
    <%
        fist_operand = request.getParameter("first-operand");
        operator = request.getParameter("operator");
        second_operand = request.getParameter("second-operand");

        Float first =  Float.parseFloat(fist_operand);
        Float second = Float.parseFloat(second_operand);


        switch (operator) {
            case "+": {
                result = first + second;
                break;
            }
            case "-": {
                result = first - second;
                break;
            }
            case "*": {
                result = first * second;
                break;
            }
            case "/": {
                result = first / second;
                break;
            }
        }
        request.setAttribute("fist_operand",fist_operand);
        request.setAttribute("second_operand",second_operand);
        request.setAttribute("operator",operator);
        request.setAttribute("result",result);

    %>
    <h1> ${fist_operand}  ${operator}  ${second_operand} =  ${result} </h1>
</body>
</html>
