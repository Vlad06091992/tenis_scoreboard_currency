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

<div class="buttons_container">
    <button class="playerButton" data-player="playerOne" type="button">Player 1 win</button>
    <button class="playerButton" data-player="playerTwo" type="button">Player 2 win</button>
</div>


<%--<form action="match-score" method="POST" style="display: inline;">--%>
<%--    <div class="form_wrapper">--%>
<%--        <div>--%>
<%--            playerEntity 1 name : <input class="player_input" type="text" name="playerEntity1" placeholder="playerEntity1"/>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            playerEntity 2 name : <input class="player_input" type="text" name="playerEntity2" placeholder="playerEntity2"/>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div class="btn_wrapper">--%>
<%--        <button class="submit_button"--%>
<%--                type="submit">--%>
<%--            Start--%>
<%--        </button>--%>
<%--    </div>--%>


<%--</form>--%>


</body>
</html>
