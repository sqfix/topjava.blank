<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 07.05.2017
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h1>Meals!</h1>
<h2><a href="index.html">Home</a></h2>

<table>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr style="color:${meal.exceed?'red':'green'}">
            <td><c:out value="${meal}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
