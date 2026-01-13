package io.microservices_java.servlets;

import io.microservices_java.dao.PlayerDao;
import io.microservices_java.entity.Player;
import io.microservices_java.service.Service;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/hm")
public class CurrenciesServlet extends HttpServlet {

    Service service = new Service();

    PlayerDao playerDao = new PlayerDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//    PrintWriter out = response.getWriter();
//            out.print("hello");

//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/jsp/hello.jsp");
        request.setAttribute("count", service.getCount());
        request.setAttribute("disabled", service.getIsDisabled());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/jsp/hello.jsp");
//        request.getRequestDispatcher("/WEB-INF/views/jsp/hello.jsp");
        requestDispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if (action.equals("increment")) {
            service.inc();
        }

        if (action.equals("decrement")) {
            service.dec();
        }

        List<Player> players = playerDao.getAllPlayers();

        // Передаем счетчик в JSP
        request.setAttribute("count", service.getCount());
        request.setAttribute("disabled", service.getIsDisabled());

        // Перенаправляем на JSP страницу
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/jsp/hello.jsp");
//        dispatcher.forward(request, response);
        requestDispatcher.forward(request, response);
    }



}