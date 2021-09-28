<%--
  Created by IntelliJ IDEA.
  User: dat01
  Date: 9/28/2021
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Calculator</title>
  </head>
  <body>
  <form action="/result.jsp" method="post">
    <label>First operand</label>
    <input type="number" name="first-operand"> <br>
    <label >Operator</label>
    <select name="operator" id="operator">
      <option value="+">+</option>
      <option value="-">-</option>
      <option value="*">x</option>
      <option value="/">/</option>
    </select> <br>
    <label>Second operand</label>
    <input type="number" name="second-operand">
    <button type="submit">submit</button>
  </form>
  </body>
</html>
