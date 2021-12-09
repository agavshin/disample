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
    <title>Item ${item.id}</title>
</head>
<body>
    <h4>Item ${item.id}</h4>
    <div>
        <b>Information about ${item.name}: </b>
        <div>
            <div>Name: ${item.name}</div>
            <div><i>Created at: ${item.createdAt}</i></div>
        </div>

        <a href="${pageContext.request.contextPath}/items">Back</a>
    </div>
</body>
</html>
