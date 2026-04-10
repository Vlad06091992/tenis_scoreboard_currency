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

<link href="${pageContext.request.contextPath}/styles/match-score.css" rel="stylesheet">


<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/match-score.js" defer></script>

</head>

<body>
<div class="header">
    <div class="link-container">
        <div>
            <a class="link" href="${pageContext.request.contextPath}/new-match">Новый матч</a>
        </div>
        <div>
            <a class="link" href="${pageContext.request.contextPath}/results">Результаты</a>
        </div>
    </div>
</div>
<div>
<c:if test="${matchViewData.taiBreak}">
    <div class="match-finished">
        Тай брейк!
    </div>
</c:if>
<c:if test="${matchViewData.finished}">
    <div class="match-finished">
        Игра закончена!
    </div>
</c:if>

<c:if test="${matchViewData.winner}">
    <div class="match-finished">
        Победитель!
        <div class="cell">${matchViewData.winner}</div>
    </div>
</c:if>

<div class="grid-container">

    <div class="cell cell_header">Player</div>
    <div class="cell cell_header">Sets</div>
    <div class="cell cell_header">Games</div>
    <div class="cell cell_header">Points</div>

    <div class="cell">${matchViewData.playerOneName}</div>
    <div class="cell">${matchViewData.playerOneSet}</div>
    <div class="cell">${matchViewData.playerOneGame}</div>
    <div class="cell">${matchViewData.playerOnePoint}</div>

    <div class="cell">${matchViewData.playerTwoName}</div>
    <div class="cell">${matchViewData.playerSecondSet}</div>
    <div class="cell">${matchViewData.playerSecondGame}</div>
    <div class="cell">${matchViewData.playerSecondPoint}</div>
</div>

<c:choose>
    <c:when test="${matchViewData.finished == true}">
        <div class="buttons_container">
            <button disabled class="playerButton" data-player="playerOne" type="button">Player 1 win</button>
            <button disabled class="playerButton" data-player="playerTwo" type="button">Player 2 win</button>
        </div>
    </c:when>
    <c:otherwise>
        <div class="buttons_container">
            <button class="playerButton" data-player="playerOne" type="button">Player 1 win</button>
            <button class="playerButton" data-player="playerTwo" type="button">Player 2 win</button>
        </div>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
