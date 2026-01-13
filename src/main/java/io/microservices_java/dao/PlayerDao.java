package io.microservices_java.dao;

import io.microservices_java.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PlayerDao {

    public List<Player> getAllPlayers() {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Player> players = session.createQuery("from Player ", Player.class).getResultList();
        session.getTransaction().commit();
        return players;
    }
}
