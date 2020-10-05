<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
<form action="meals" method="post">
    DateTime: <input type="datetime-local" name="dateTime" value="${meal.dateTime}" />
    <br>
    Description: <input name="description" value="${meal.description}"/>
    <br>
    Calories: <input name="calories" value="${meal.calories}"/>
    <br>
    <input type="submit" value="Save" /> <a href="meals">Cancel</a>
    <input type="hidden" name="id" value="${meal.id}"/>

</form>
</body>
</html>
