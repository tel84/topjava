<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>
<h2><a href="meals">Meal List</a></h2>
<form method="POST" action='meals'>
    <table>
        <tr>
            <td>ID :</td>
            <td><input type="text" readonly="readonly" name="id" value=${meal.id}></td>
        </tr>
        <tr>
            <td>Описание :</td>
            <td><input type="text" name="description" value="${meal.description}"/></td>
        </tr>
        <tr>
            <td>Калории :</td>
            <td><input type="text" name="calories" value="${meal.calories}"/></td>
        </tr>
        <tr>
            <td>Дата и время :</td>
            <td><input type="datetime" name="dateTime" value="${meal.dateTime}"/></td>
        </tr>
    </table>
    <input type="submit" value="${meal.id>-1 ? "Изменить":"Добавить"}"/>
</form>

</body>
</html>
