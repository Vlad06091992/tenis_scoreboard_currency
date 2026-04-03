package io.microservices_java.servlets;

import io.microservices_java.dao.Match;
import io.microservices_java.dao.MatchDao;
import io.microservices_java.entity.MatchData;
import io.microservices_java.entity.MatchViewData;
import io.microservices_java.service.MatchOnGoingProcessor;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@WebServlet("/results")
public class ResultsServlet extends HttpServlet {

    MatchOnGoingProcessor matchOnGoingProcessor;
    MatchDao matchDao;

    @Override
    public void init() throws ServletException {
        matchOnGoingProcessor = (MatchOnGoingProcessor) getServletContext().getAttribute("matchOnGoingProcessor");
        matchDao = (MatchDao) getServletContext().getAttribute("matchDao");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = URLEncoder.encode(request.getParameter("name") != null ? request.getParameter("name") : "", StandardCharsets.UTF_8);
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