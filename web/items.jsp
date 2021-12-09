<%--
  Created by IntelliJ IDEA.
  User: ruagsax
  Date: 10.11.2021
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
</head>
<body>
    <h4>Items</h4>
    <div>
        <b>Information: </b>
        <c:forEach items="${items}" var="item">
            <div>Name: ${item.name} <a href="items/${item.id}">--></a></div>
        </c:forEach>
    </div>

    <a href="/">Back</a>
</body>
</html>
