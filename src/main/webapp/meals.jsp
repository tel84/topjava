<%--  Created by IntelliJ IDEA.
  User: Максим
  Date: 20.01.2017
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Meal list</title>
</head>
<style>
    .green{color: green}
    .red{color: red}
</style>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table border="1">
    <th>Дата и время</th>
    <th>Описание</th>
    <th>Каллории</th>
    <c:forEach items="${mealList}" var="meal" >
     <tr class="${meal.exceed ? 'red' : 'green'}">
         <c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}" />
         <fmt:parseDate value="${cleanedDateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate" type="date"/>
         <fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd HH:mm" var="date"/>
          <td>${date}</td>
          <td>${meal.description}</td>
          <td>${meal.calories}</td>
        </tr>
</c:forEach>
        </table>

</body>
</html>
