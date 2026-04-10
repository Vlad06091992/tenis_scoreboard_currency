<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14.01.2026
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<link rel="stylesheet" href="<c:url value='/WEB-INF/styles/new-match.css'/>">--%>

<link href="${pageContext.request.contextPath}/styles/new-match.css" rel="stylesheet">


<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/new-match.js" defer></script>
</head>
<body>
<div class="header">
    <div class="link-container">
        <div>
            <a class="link" href="${pageContext.request.contextPath}/results">Результаты</a>
        </div>
    </div>
</div>

<div class="container">
    <form action="new-match" method="POST">
        <div class="form_wrapper">
            <div class="input-container">
                <input class="player_input" type="text" name="playerEntity1" placeholder="Enter first playerEntity name"/>
            </div>
            <div class="input-container">
                <input class="player_input" type="text" name="playerEntity2" placeholder="Enter second playerEntity name"/>
            </div>
        </div>

        <div class="btn_wrapper">
            <button class="submit_button" type="submit">
                Start Match
            </button>
        </div>
    </form>
</div>


</body>
</html>
