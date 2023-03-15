package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Card;
import org.example.dto.Terminal;
import org.example.util.TerminalUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TerminalRepository {

    public int addTerminalToDb(Terminal terminal) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into terminal(code,address,created_date,status) " +
                            "values (?,?,?,?)");
            statement.setString(1, terminal.getCode());
            statement.setString(2, terminal.getAddress());
            statement.setTimestamp(3, Timestamp.valueOf(terminal.getCreated_date()));
            statement.setString(4, terminal.getStatus());

            int resultSet = statement.executeUpdate();
            return resultSet;


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return 0;

    }


    public List<Terminal> getTerminal(String code) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Terminal> terminals = session.createQuery("SELECT a from Terminal a where code = '" + code + "'", Terminal.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return terminals;
    }

    public List<Terminal> get_terminal_list_fromDb() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Terminal> terminals = session.createQuery("SELECT a from Terminal a ", Terminal.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return terminals;
    }

    public int updateTerminal_address_fromDB(String code, String address) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update terminal set address=? where code=? ;");
            statement.setString(1, address);
            statement.setString(2, code);

            int resultSet = statement.executeUpdate();

            return resultSet;


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return 0;


    }

    public int changeTerminal_status_fromDB(String code, String status) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("update terminal set status=? where code=? ;");
            statement.setString(1, status);
            statement.setString(2, code);

            int resultSet = statement.executeUpdate();

            return resultSet;


        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        return 0;


    }

    public int deleteTerminal_fromDb(String code) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE from Terminal where code = '" + code + "'", Card.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return 0;
    }

    public int changeTerminal_ACTIVE_status_fromDB(String code, String status) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Terminal set status = :status" +
                " where code = :code");
        query.setParameter("status", status);
        query.setParameter("code", code);
        int result = query.executeUpdate();
        session.getTransaction().commit();

        session.close();
        factory.close();
        return 1;
    }

    public int changeTerminal_BLOCK_status_fromDB(String code, String status) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Terminal set status = :status" +
                " where code = :code");
        query.setParameter("status", status);
        query.setParameter("code", code);
        int result = query.executeUpdate();
        session.getTransaction().commit();

        session.close();
        factory.close();
        return 1;
    }
}
