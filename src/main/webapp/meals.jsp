<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<p><a href="updateMeal.jsp">Add Meal</a></p>
<table border="1" cellpadding="7">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr style="color: <c:out value="${meal.excess ? 'red' : 'green'}"/>">
            <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="meals?action=update&id=<c:out value="${meal.id}"/>"/>Update</td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>"/>Delete</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
