package io.microservices_java.context;
import io.microservices_java.dao.MatchDao;
import io.microservices_java.dao.PlayerDao;
import io.microservices_java.service.MatchOnGoingProcessor;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.*;


// Class
@WebListener
public class MyListenerGfg implements ServletContextListener {
    // Class data members
    ServletContext ctx;
    Connection con;
    Statement s;
    PreparedStatement ps;
    ResultSet rs;
    int count;

    // Method 1
    public void contextInitialized(ServletContextEvent sce) {
        try {
            PlayerDao pdao = new PlayerDao();
            MatchDao matchDao = new MatchDao();
            MatchOnGoingProcessor matchOnGoingProcessor = new MatchOnGoingProcessor();

            ctx = sce.getServletContext();
//            ctx.setAttribute("pcount", count);
            ctx.setAttribute("pdao", pdao);
            ctx.setAttribute("matchDao", matchDao);
            ctx.setAttribute("matchOnGoingProcessor", matchOnGoingProcessor);
        }

        // Catch block to handle exceptions
        catch (Exception e) {

            // Display exception with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }
}