package org.example.repository;

import org.example.dto.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class CardRepository {

    public int addCardToDb(Card card) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(card);
        transaction.commit();

        session.close();
        factory.close();
        return 0;
    }


    public List<Card> get_card_list() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Card> cards = session.createQuery("SELECT a from Card a", Card.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return cards;

    }


    public List<Card> searchCardByNumber(String number) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Card> cards = session.createQuery("SELECT a from Card a where number = '" + number + "'", Card.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return cards;
    }

    public int updateCardFromDb(Card card) {


        return 0;

    }

    public int changeACTIVEStatus(String number, String status) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Card set status = :status" +
                " where number = :number");
        query.setParameter("status", status);
        query.setParameter("number", number);
        int result = query.executeUpdate();
        session.getTransaction().commit();

        session.close();
        factory.close();
        return 1;
    }

    public int changeBLOCKStatus(String number, String status) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Card set status = :status" +
                " where number = :number");
        query.setParameter("status", status);
        query.setParameter("number", number);
        int result = query.executeUpdate();
        session.getTransaction().commit();

        session.close();
        factory.close();
        return 1;
    }


    public int deleteCardFromDb(String number) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE from Card where number = '" + number + "'", Card.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return 0;
    }

    public int addPhone_to_Card(String number, String phone) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE Card set phone = " + phone + " where number = " + number + "", Card.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return 1;
    }

    public List<Card> get_profile_card_list_fromDb(String phone) {
        return null;
    }

    public int change_profile_card_status_fromDB(String phone, String number, String status) {

        return 0;
    }

    public int delete_phone_from_card(String number) {

        return 0;

    }

    public List<Card> companyCard() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Card> cards = session.createQuery("SELECT a from Card a where exp_date = " + null + "", Card.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return cards;
    }
}
