package io.tennis_scoreboard.context;
import io.tennis_scoreboard.dao.MatchDao;
import io.tennis_scoreboard.service.MatchOnGoingProcessor;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


// Class
@WebListener
public class MyListenerGfg implements ServletContextListener {
    ServletContext ctx;

    public void contextInitialized(ServletContextEvent sce) {
        try {
            MatchDao matchDao = new MatchDao();
            MatchOnGoingProcessor matchOnGoingProcessor = new MatchOnGoingProcessor();

            ctx = sce.getServletContext();
            ctx.setAttribute("matchDao", matchDao);
            ctx.setAttribute("matchOnGoingProcessor", matchOnGoingProcessor);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}