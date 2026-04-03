<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.03.2026
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/styles/results.css" rel="stylesheet">
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/results.js" defer></script>
</head>
<body>
<div class="header">
    <div>
        <a href="${pageContext.request.contextPath}/new-match">Новый матч</a>
        <a href="${pageContext.request.contextPath}/results">Результаты</a>
    </div>
</div>
<div class="container">
<form autocomplete="off" action="results" method="POST">
    <div class="input-container">
        <input autocomplete="off" disabled class="player_input" type="text" name="name" placeholder="Enter player name">
        <button type="button" class="clean-button" disabled >Очистить</button>
        <button disabled type="submit" class="search" name="page" value="search">Искать</button>
    </div>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>PLAYER ONE</th>
                <th>PLAYER TWO</th>
                <th>WINNER</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${matches}" var="match">
                <tr>
                    <td><c:out value="${match.id}" /></td>
                    <td><c:out value="${match.playerOne}" /></td>
                    <td><c:out value="${match.playerTwo}" /></td>
                    <td><c:out value="${match.winner}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="input-container">
        <button disabled type="submit" class="prev" name="page" value="prev">Назад</button>
        <button disabled type="submit" class="next" name="page" value="next">Далее</button>
    </div>
</form>
</div>
</body>
</html>
