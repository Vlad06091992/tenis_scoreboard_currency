package io.microservices_java.servlets;

import io.microservices_java.service.MatchService;
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

    MatchService matchService;

    @Override
    public void init() throws ServletException {
         matchService = (MatchService) getServletContext().getAttribute("matchService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/jsp/new-match.jsp");
        requestDispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       String player1 = request.getParameter("playerEntity1");
       String player2 = request.getParameter("playerEntity2");

        PrintWriter out = response.getWriter();

       UUID newGameId = UUID.randomUUID();
       matchService.startMatch(newGameId, player1, player2);

        if(player1.equals("ваня")){
            response.setStatus(400);
            out.print("Абшибка");
            request.setAttribute("message", "нихуя себ");
            response.sendRedirect("/tennis_scoreboard/new-match" );
            return;
        }

        response.sendRedirect( request.getContextPath() + "/match-score" + '/' + newGameId);
    }



}