<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <style>
        .blueText {
            color: blue;
        }
        .redText {
            color: red;
        }
    </style>

</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Users</h2>


<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach var="meal" items="${requestScope.meals}">
        <%--c:forEach items="${meals}" var="meal"--%>
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <%--c:choose--%>
        <tr>
            <td>
                _=_

            </td>
            <span class="blueText"> tetet
            <td>  <span class="blueText"> : ggg  ${meal}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
        <%--/c:choose--%>;
    </c:forEach>
</table>
</body>
</html>