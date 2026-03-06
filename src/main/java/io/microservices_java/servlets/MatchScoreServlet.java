package io.microservices_java.servlets;

import io.microservices_java.entity.MatchData;
import io.microservices_java.entity.MatchViewData;
import io.microservices_java.service.MatchService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match-score/*")
public class MatchScoreServlet extends HttpServlet {

    MatchService matchService;

    @Override
    public void init() throws ServletException {
        matchService = (MatchService) getServletContext().getAttribute("matchService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String matchId = request.getPathInfo().substring(1);

        //берем матч, матч должен с помощью хелпера корректно в себе инкаспулировать логику игры
        MatchData matchData = matchService.getMatchData(UUID.fromString(matchId));
        MatchViewData matchViewData = matchData.getMatchViewData();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/jsp/match-score.jsp");

        System.out.println(matchViewData.playerOneGame);

        request.setAttribute("matchViewData", matchViewData);
        requestDispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String matchId = request.getPathInfo().substring(1);
        // Читаем JSON из тела запроса
        BufferedReader reader = request.getReader();
        String player = "";
        String line;
        while ((line = reader.readLine()) != null) {

            if (line.contains("playerOne")) {
                player = "playerOne";
            }

            if (line.contains("playerTwo")) {
                player = "playerTwo";
            }
        }


        matchService.updateScore(UUID.fromString(matchId), player);
        matchService.updateMatchProgress(UUID.fromString(matchId), player);
        response.sendRedirect(request.getContextPath() + "/match-score" + '/' + matchId);
    }



}