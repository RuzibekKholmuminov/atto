package org.example.repository;

import org.example.db.DataBase;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.util.ProfileUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ProfileRepository {
    public Profile searchByPhone(String phone) {


        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from profile where phone=? ;");
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return ProfileUtil.get_profile(resultSet);
            }


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

        return null;
    }

    public int addProfileToDb(Profile profile) {

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into profile(name,surname,phone,password,created_date,status,role) " +
                            "values (?,?,?,?,?,?,?)");
            statement.setString(1, profile.getName());
            statement.setString(2, profile.getSurname());
            statement.setString(3, profile.getPhone());
            statement.setString(4, profile.getPassword());
            statement.setTimestamp(5, Timestamp.valueOf(profile.getCreated_date()));
            statement.setString(6, profile.getStatus().name());
            statement.setString(7, profile.getRole().name());

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

    public int deleteProfileToDb() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String psw = scanner.next();
        if (psw.equals("123")){
            System.out.print("Enter name ADMIN: ");
            String name = scanner.next();

            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
            SessionFactory factory = meta.getSessionFactoryBuilder().build();

            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE from Profile where name = '" + name + "'", Card.class).getResultList();

            transaction.commit();

            session.close();
            factory.close();
        }else {
            System.err.println("Mazgi sen ADMIN larni o'chirolmiysan");
            return 0;
        }
        return 1;
    }


    public List<Profile> get_profile_list_fromDb() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Profile> profileList = session.createQuery("SELECT a from Profile a", Profile.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return profileList;
    }

    public List<Profile> get_profile(int id) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Profile> profileList = session.createQuery("SELECT a from Profile a where id = " + id + "", Profile.class).getResultList();

        transaction.commit();

        session.close();
        factory.close();
        return profileList;
    }

    public int changeACTIVEstatus(Integer id, String status) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Profile set status = :status" +
                " where id = :id");
        query.setParameter("status", status);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();

        session.close();
        factory.close();
        return 1;
    }

    public int changeBLOCKstatus(Integer id, String status) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory = meta.getSessionFactoryBuilder().build();

        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Profile set status = :status" +
                " where id = :id");
        query.setParameter("status", status);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();

        session.close();
        factory.close();
        return 1;

    }
}
