package io.tennis_scoreboard.dao;

import io.tennis_scoreboard.entity.MatchData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class MatchDao {
    private final SessionFactory sessionFactory;
    public MatchDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Match> getAllMatches(String name, String page) {
        Session session = sessionFactory.getCurrentSession();

        try {
            int limit = 5;
            int offset = Integer.parseInt(page) == 1 ? 0 : (Integer.parseInt(page) - 1) * limit;

            session.beginTransaction();
            String query = name.isEmpty() ? "from Match" : "from Match where winner = '" + name + "'";
            List<Match> playerEntities = session.createQuery(query, Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
            session.getTransaction().commit();
            return playerEntities;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Failed to get all players", e);
        }
    }

    public void addMatch(MatchData matchData) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Match match = new Match();
            match.setId(matchData.getMatchId());
            match.setPlayerOne(matchData.getPlayer1());
            match.setPlayerTwo(matchData.getPlayer2());
            match.setWinner(matchData.getWinner());

            session.merge(match);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Failed to create match: " + e);
        } finally {
            session.close();
        }
    }
}