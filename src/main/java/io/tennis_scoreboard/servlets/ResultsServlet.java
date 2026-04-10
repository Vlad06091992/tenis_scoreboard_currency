package io.tennis_scoreboard.servlets;

import io.tennis_scoreboard.dao.Match;
import io.tennis_scoreboard.dao.MatchDao;
import io.tennis_scoreboard.service.MatchOnGoingProcessor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {

    MatchOnGoingProcessor matchOnGoingProcessor;
    MatchDao matchDao;

    @Override
    public void init() {
        matchOnGoingProcessor = (MatchOnGoingProcessor) getServletContext().getAttribute("matchOnGoingProcessor");
        matchDao = (MatchDao) getServletContext().getAttribute("matchDao");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name") != null ? request.getParameter("name") : "";
        String page = request.getParameter("page");
        if (name == null) name = "";
        String number = page == null ? "1" : page;
        List<Match> matches = matchDao.getAllMatches(name, number);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/jsp/results.jsp");


        request.setAttribute("matches", matches);
        requestDispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = URLEncoder.encode(request.getParameter("name") != null ? request.getParameter("name") : "", StandardCharsets.UTF_8);
        String page = request.getParameter("page");

        if (name == null) name = "";
        String number = page == null ? "1" : page;
        List<Match> matches = matchDao.getAllMatches(name, number);


        request.setAttribute("matches", matches);
        String redirectUrl = request.getContextPath() + "/results?name=" + name + "&page=" + page;
        response.sendRedirect(redirectUrl);
    }
}