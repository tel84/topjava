<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Meal list</title>
</head>
<style>
    .green {
        color: green
    }

    .red {
        color: red
    }
</style>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<table border="1">
    <th>ID</th>
    <th>Дата и время</th>
    <th>Описание</th>
    <th>Каллории</th>
    <th colspan=2>Action</th>
    <c:forEach items="${mealList}" var="meal">
        <tr class="${meal.exceed ? 'red' : 'green'}">
            <td>${meal.id}</td>
            <c:set var="cleanedDateTime" value="${fn:replace(meal.dateTime, 'T', ' ')}"/>
            <fmt:parseDate value="${cleanedDateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate" type="date"/>
            <fmt:formatDate value="${parsedDate}" type="date" pattern="yyyy-MM-dd HH:mm" var="date"/>
            <td>${date}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Редактировать</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="meals?action=insert">Add User</a></p>
</body>
</html>
