package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Card;
import org.example.dto.Profile_Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Profile_Card_Repository {
    public int addCard_to_user(Profile_Card profile_card) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(profile_card);
        transaction.commit();

        session.close();
        factory.close();
        return 0;
    }


    public int delete_profile_card(String phone, String number) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE from Profile_Card where card_number = '" + number + "' and profile_phone = '" + phone + "'", Profile_Card.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return 0;
    }
}
