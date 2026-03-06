package io.microservices_java.dao;

import io.microservices_java.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PlayerDao {

    private final SessionFactory sessionFactory;

    // Конструктор с инъекцией SessionFactory
    public PlayerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Или можно оставить получение через HibernateUtil
    public PlayerDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Player> getAllPlayers() {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            List<Player> playerEntities = session.createQuery("from Player", Player.class)
                    .getResultList();
            session.getTransaction().commit();
            return playerEntities;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Failed to get all players", e);
        }
    }

    public Optional<Player> getPlayerByName(String playerName) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            String hql = "FROM Player p WHERE p.name = :playerName";
            Query<Player> query = session.createQuery(hql, Player.class);
            query.setParameter("playerName", playerName);

            Player playerEntity = query.uniqueResult();
            session.getTransaction().commit();

            return Optional.ofNullable(playerEntity);
        } catch (Exception e) {
            session.getTransaction().rollback();
            // Можно вернуть Optional.empty() или бросить исключение
            // Лучше бросить, чтобы знать об ошибке
            throw new RuntimeException("Failed to get player: " + playerName, e);
        }
    }

    public Player createPlayer(String playerName) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Player newPlayerEntity = new Player();
            newPlayerEntity.setName(playerName);

            session.persist(newPlayerEntity);
            session.getTransaction().commit();

            return newPlayerEntity;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Failed to create player: " + playerName, e);
        }
    }

    // Дополнительные методы с транзакциями:

//    public void updatePlayerScore(Long playerId, int newScore) {
//        Session session = sessionFactory.getCurrentSession();
//
//        try {
//            session.beginTransaction();
//
//            Player player = session.get(Player.class, playerId);
//            if (player != null) {
////                player.setScore(newScore);
//                session.update(player); // Не обязательно, Hibernate сам отследит изменения
//            }
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            session.getTransaction().rollback();
//            throw new RuntimeException("Failed to update player score", e);
//        }
//    }

    public boolean deletePlayer(Long playerId) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Player playerEntity = session.get(Player.class, playerId);
            if (playerEntity != null) {
                session.delete(playerEntity);
                session.getTransaction().commit();
                return true;
            } else {
                session.getTransaction().commit();
                return false;
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new RuntimeException("Failed to delete player", e);
        }
    }
}