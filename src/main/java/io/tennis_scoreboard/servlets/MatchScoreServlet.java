package io.tennis_scoreboard.servlets;

import io.tennis_scoreboard.dao.MatchDao;
import io.tennis_scoreboard.entity.MatchData;
import io.tennis_scoreboard.entity.MatchViewData;
import io.tennis_scoreboard.service.MatchOnGoingProcessor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score/*")
public class MatchScoreServlet extends HttpServlet {

    MatchOnGoingProcessor matchOnGoingProcessor;
    MatchDao matchDao;

    @Override
    public void init() {
        matchOnGoingProcessor = (MatchOnGoingProcessor) getServletContext().getAttribute("matchOnGoingProcessor");
        matchDao = (MatchDao) getServletContext().getAttribute("matchDao");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String matchId = request.getPathInfo().substring(1);
        MatchData matchData = matchOnGoingProcessor.getMatchData(UUID.fromString(matchId));
        MatchViewData matchViewData = matchData.getMatchViewData();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/jsp/match-score.jsp");

        request.setAttribute("matchViewData", matchViewData);
        requestDispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String matchId = request.getPathInfo().substring(1);
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


        UUID uuid = UUID.fromString(matchId);
        matchOnGoingProcessor.updateMatchProgress(uuid, player);

        MatchData matchData = matchOnGoingProcessor.getMatchData(uuid);

        if(matchData.getIsFinished()){
            matchDao.addMatch(matchData);
        }
            response.sendRedirect(request.getContextPath() + "/match-score" + '/' + matchId);
    }
}