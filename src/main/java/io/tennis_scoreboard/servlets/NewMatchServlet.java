package io.tennis_scoreboard.servlets;

import io.tennis_scoreboard.service.MatchOnGoingProcessor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    MatchOnGoingProcessor matchOnGoingProcessor;

    @Override
    public void init() {
         matchOnGoingProcessor = (MatchOnGoingProcessor) getServletContext().getAttribute("matchOnGoingProcessor");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/jsp/new-match.jsp");
        requestDispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       String player1 = request.getParameter("playerEntity1");
       String player2 = request.getParameter("playerEntity2");

        PrintWriter out = response.getWriter();



        if(player1.equals("ваня")){
            response.setStatus(400);
            out.print("Абшибка");
            request.setAttribute("message", "нихуя себ");
            response.sendRedirect("/tennis_scoreboard/new-match" );
            return;
        }

        UUID newGameId = UUID.randomUUID();
        matchOnGoingProcessor.startMatch(newGameId, player1, player2);

        response.sendRedirect( request.getContextPath() + "/match-score" + '/' + newGameId);
    }



}