package org.example.repository;

import org.example.db.DataBase;
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

        Connection connection = DataBase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("delete from profile_card where profile_phone=? and card_number=?;");
            statement.setString(1, phone);
            statement.setString(2, number);

            return statement.executeUpdate();


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
}
